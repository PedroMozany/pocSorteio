import React from 'react';
import { useState, useEffect } from 'react';
import { MapTo } from "@adobe/aem-react-editable-components";
import { Button } from '../micro/Button/Button';
import { Title } from '../Micro/Title/Title';
import { Text } from '../Micro/Text/Text';
import {
    SorteioContainer,
    CardStyled,
    CadastrosContainer,
    TrStyled,
    PostsContainer,
    TitleContainer,
    DeleteContainer
} from './IntraSorteioStyled';
import Table from '../Table/Table';
import { useHistory } from "react-router-dom"
import axios from 'axios';
import reactStringReplace from 'react-string-replace';


const IntraSorteio = ({ titleText, sorteioTextButton }) => {
    const username = 'admin';
    const secret = 'admin';
    const token = Buffer.from(username + ':' + secret).toString('base64');
    const baseURL = "/content/raffle/winner.model.json";
    const tableBaseUrl = "/content/api/sorteio.model.json";
    const logoutUrl = "/content/api/login.model.json";

    const history = useHistory();

    const [posts, setPosts] = useState([])
    const [winners, setWinners] = useState('')


    const [status, setStatus] = useState(null);
    const [errorMessage, setErrorMessage] = useState(null);

    useEffect(() => {
        axios.get(tableBaseUrl, {
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Basic ${token}`
            },
        })
            .then((response) => {
                setPosts(response.data)
                console.log(response.data)
                console.log(posts)
            })
            .catch(() => {
                console.log("deu errado")
            })
    }, [])

    function Raffle() {

        axios.get(baseURL, {
            headers: {
                'Content-Type': 'multipart/form-data',
                'Authorization': `Basic ${token}`
            },
        })
            .then((response) => {
                setWinners(response.data);

            })
            .catch(() => {
                console.log("deu errado o sorteio")
            })

    };

    function DeleteAll() {       
            axios.delete(tableBaseUrl)
                .then(response => setStatus('Delete successful'))
                .then(response => setPosts([]))
                .catch(error => {
                    setErrorMessage(error.message);
                    console.error('There was an error!', error);
                });      
    } 

    const Return = () => {
        history.push("content/sorteio/br/pt/home")
    }

    function Logout (){
        axios.delete(logoutUrl)
        .then(response => setStatus('Logout successful'))
        .then(response => setPosts([]))
        .then(Return)
        .catch(error => {
            setErrorMessage(error.message);
            console.error('There was an error!', error);
        });      
    }

    //let winnerArray = winners.split(",")
    //let winnerName = (winnerArray[0].slice(14))
    //console.log(winners)
    //let winnerEmail = (winners.slice(30))

return (

    <>
        <TitleContainer>
            <Title
                text={titleText}>
            </Title>
            <Button
                type="button"
                id="adm"
                textButton="Sair"
                onClick={Logout}
            />
        </TitleContainer>
        <SorteioContainer>
            <CardStyled>
                <Text
                    text="Nome:"></Text>
                <Text variable={winners.name} />
            </CardStyled>
            <CardStyled>
                <Text text="Email:" />
                <Text variable={winners.email} />
            </CardStyled>
            <Button
                type="button"
                textButton={sorteioTextButton}
                onClick={Raffle}
            >
            </Button>
        </SorteioContainer>
        <CadastrosContainer>
            <TitleContainer>
                <Title
                    id="intraTitle"
                    text="Cadastrados">
                </Title>
                <DeleteContainer>
                    <Button
                        type="button"
                        id="delete"
                        textButton="Limpar cadastros"
                        onClick={DeleteAll}
                    />
                    <Text
                        id="warning"
                        text="(Ao clicar nesse botão todos os nomes cadastrados serão excluídos."
                    />
                </DeleteContainer>
            </TitleContainer>
            <Table 
            list={posts == [] ? <TrStyled>Não há cadastros</TrStyled> : posts.map((post, index) => {
                return (
                    <PostsContainer key={index}>
                        <TrStyled>
                            {post.name}
                        </TrStyled>
                        <TrStyled>
                            {post.email}
                        </TrStyled>
                    </PostsContainer>
                )})}
            />             
        </CadastrosContainer>      
    </>
)
}

IntraSorteio.defaultProps = {

    sorteioTextButton: "Sortear",
    titleText: "Sorteio",
};


export default MapTo("sorteio/components/intra-sorteio")(IntraSorteio);