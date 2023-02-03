import React from 'react';
import { MapTo } from "@adobe/aem-react-editable-components";
import { SuccessContainer } from './SuccessStyled';
import { Title } from '../Micro/Title/Title';
import { Text } from '../Micro/Text/Text';
import { Button } from '../micro/Button/Button';
import {useHistory} from "react-router-dom"

const Success = ({successTitle, successText, successBtn}) => {

    const history = useHistory();

    const Return = () => {
        history.push("content/sorteio/br/pt/home")
    }

    return(
       <SuccessContainer>
            <Title
            text={successTitle}/>
            <Text 
            id="textTable"
            text={successText}
            />
            <Button
            type="button"
            id="adm"
            onClick={Return}
            textButton={successBtn}/>
       </SuccessContainer>

    )
}
Success.defaultProps = {

    successTitle: "Cadastro concluído.",
    successText: "O seu cadastro foi realizado com sucesso.",
    successBtn: "voltar"
    
};

export default MapTo("sorteio/components/success")(Success);