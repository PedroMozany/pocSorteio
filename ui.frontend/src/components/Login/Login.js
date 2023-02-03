import React from 'react';
import { useState, useRef, useEffect, useContext } from 'react';
import { MapTo } from "@adobe/aem-react-editable-components";
import { Input } from '../micro/input/Input';
import { Text } from '../Micro/Text/Text'
import {
    FormContainer,
    ButtonContainer,
    TitleContainer,
    Container,
    ErrorStyled,
    ErrorContainer,
    ButtonLoginContainer,
} from './LoginStyled';
import { Button } from '../micro/Button/Button';
import { Title } from '../Micro/Title/Title';
import { useForm } from 'react-hook-form';
import { useHistory } from "react-router-dom";
import axios from 'axios';


const FormLogin = ({ titleText, userPlaceholderText, userLabelText, placeholderPasswordText, labelPasswordText, buttonText }) => {

    const {
        register,
        handleSubmit,
        formState: { errors },
    } = useForm();

    const history = useHistory();

    const baseURL = "/content/api/login.model.json";

    const [loginError, setLoginError] = useState("")

    const onSubmitHandler = async (data) => {
        console.log(data.name)
        console.log(data.password)

        const LoginFormData = new FormData();
        LoginFormData.append("name", data.name)
        LoginFormData.append("password", data.password)

        try {

            const response = await axios({
                method: "post",
                url: baseURL,
                data: LoginFormData,
                headers: { "Content-Type": "multipart/form-data" },
            });
            history.push("content/sorteio/br/pt/home/IntraSorteio")
        } catch (error) {
            console.log(error)
            setLoginError("Atenção: Usuário ou senha inválidos.")
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
                        placeholder={userPlaceholderText}
                        label={userLabelText}
                        type="text"
                        isError={errors.user}
                        {...{
                            register: register('name', { required: true })
                        }}
                    />
                    <ErrorContainer>
                        {(errors.name && (
                            <ErrorStyled>Insira um usuário válido.</ErrorStyled>
                        ))}
                    </ErrorContainer>
                    <Input
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
                            <ErrorStyled>Senha inválida.</ErrorStyled>
                        ))}
                    </ErrorContainer>
                </>
                <Text
                id="error"
                text={loginError = "" ? " " : loginError}
                />
                <ButtonContainer>
                    <Button
                        type="submit"
                        textButton={buttonText}
                    />
                </ButtonContainer>
            </FormContainer>
            <ButtonLoginContainer>
                <Button
                    type="button"
                    textButton="voltar"
                    onClick={Return}
                    id="adm">
                </Button>
            </ButtonLoginContainer>
        </Container>
    )
};

FormLogin.defaultProps = {

    userPlaceholderText: "Seu Nome",
    userLabelText: "Usuário",
    buttonText: "Entrar",
    titleText: "Login",
    placeholderPasswordText: "sua senha",
    labelPasswordText: "Senha"
};

export default MapTo("sorteio/components/form-login")(FormLogin);