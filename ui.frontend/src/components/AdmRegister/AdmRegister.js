import React from 'react';
import { MapTo } from "@adobe/aem-react-editable-components";
import { Input } from '../micro/input/Input';
import {
    FormContainer,
    ButtonContainer,
    TitleContainer,
    Container,
    ErrorContainer,
    BtAdmContainer,
    ErrorStyled
} from './AdmRegisterStyled'
import { Button } from '../micro/Button/Button';
import { Title } from '../Micro/Title/Title';
import { useForm } from 'react-hook-form';
import { useHistory } from "react-router-dom"
import axios from 'axios'

const AdmRegister = ({ titleText, userPlaceholderText, userLabelText, placeholderPasswordText, labelPasswordText, buttonText }) => {

    const {
        register,
        handleSubmit,
        formState: { errors },
    } = useForm();

    const history = useHistory();
    const baseURL = "http://localhost:4502/content/api/signUp.model.json";

    const onSubmitHandler = async (data) => {
        console.log(data.name)
        console.log(data.password)

        const RegisterFormData = new FormData();
        RegisterFormData.append("name", data.name)
        RegisterFormData.append("password", data.password)

        try {

            const response = await axios({
                method: "post",
                url: baseURL,
                data: RegisterFormData,
                headers: { "Content-Type": "multipart/form-data" },
            })
            history.push("content/sorteio/br/pt/home/success");
        } catch (error) {
            console.log(error)
        }
    }
    const Return = () => {
        history.push("content/sorteio/br/pt/home")
    }

    return (
        <Container>
            <TitleContainer>
                <Title
                    text={titleText}>
                </Title>
            </TitleContainer>
            <FormContainer onSubmit={handleSubmit(onSubmitHandler)}>
                <>
                    <Input
                        type="text"
                        placeholder={userPlaceholderText}
                        label={userLabelText}
                        id="name"
                        isError={errors.name}
                        {...{
                            register: register('name', { required: true })
                        }}
                    />
                    <ErrorContainer>
                        {(errors.name && (
                            <ErrorStyled>Insira um nome válido.</ErrorStyled>
                        ))}
                    </ErrorContainer>
                    <Input

                        id="password"
                        placeholder={placeholderPasswordText}
                        label={labelPasswordText}
                        type="password"
                        isError={errors.password}
                        {...{
                            register: register('password', { required: true })
                        }}
                    />
                    <ErrorContainer>
                        {(errors.password && (
                            <ErrorStyled>A senha está incorreta.</ErrorStyled>
                        ))}
                    </ErrorContainer>
                </>
                <ButtonContainer>
                    <Button
                        type="submit"
                        textButton={buttonText}
                    />
                </ButtonContainer>
            </FormContainer>
            <BtAdmContainer>
                <Button
                    type="button"
                    textButton="voltar"
                    id="adm"
                    onClick={Return}
                    >
                </Button>
            </BtAdmContainer>
        </Container>
    )
};

AdmRegister.defaultProps = {

    userPlaceholderText: "Seu Nome",
    userLabelText: "Usuário",
    buttonText: "Enviar",
    titleText: "Cadastro ADM",
    placeholderPasswordText: "sua senha",
    labelPasswordText: "Senha"
};

export default MapTo("sorteio/components/adm-register")(AdmRegister);