import React from 'react';
import { MapTo } from "@adobe/aem-react-editable-components";
import { Input } from '../micro/input/Input';
import { FormContainer } from './FormSorteioStyled';
import { ButtonContainer } from './FormSorteioStyled';
import { TitleContainer } from './FormSorteioStyled';
import { Container } from './FormSorteioStyled';
import { Button } from '../micro/Button/Button';
import { Title } from '../Micro/Title/Title';
import { ErrorStyled } from './FormSorteioStyled';
import { ErrorContainer } from './FormSorteioStyled';
import { ButtonLoginContainer } from './FormSorteioStyled';
import { useForm } from 'react-hook-form';
import { useState } from 'react';
import { useHistory } from "react-router-dom"
import axios from 'axios'

const FormSorteio = ({ titleText, namePlaceholderText, nameLabelText, placeholderEmailText, labelEmailText, buttonText }) => {

    const {
        register,
        handleSubmit,
        formState: { errors },
    } = useForm();

    const history = useHistory();   
    const baseURL = "/content/api/sorteio.model.json";
   
    
    const onSubmitHandler = async(data) => {
           console.log(data.name)
           console.log(data.email)
       
        const RegisterFormData = new FormData();
        RegisterFormData.append("name", data.name)
        RegisterFormData.append("email", data.email)
       
        try {
         
          const response = await axios({
            method: "post",
            url: baseURL,
            data: RegisterFormData,
            headers: { "Content-Type": "multipart/form-data" },
          })
          history.push("content/sorteio/br/pt/home/success");
        } catch(error) {
          console.log(error)
        }
    }

   function goToAdmRegister() {
        history.push("content/sorteio/br/pt/home/admRegister.html")
    };

    function goToAdmLogin() {
        history.push("content/sorteio/br/pt/home/login.html")
    };

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
                        placeholder={namePlaceholderText}
                        label={nameLabelText}
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

                        id="email"
                        placeholder={placeholderEmailText}
                        label={labelEmailText}
                        type="email"   
                        isError={errors.email}                                              
                        {...{
                            register: register('email', { required: true })
                        }}
                    />
                    <ErrorContainer>
                        {(errors.email && (
                            <ErrorStyled>Insira um email válido.</ErrorStyled>
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
            <ButtonLoginContainer>
                <Button
                    type="button"
                    textButton="cadastro adm"
                    onClick={goToAdmRegister}
                    id="adm">

                </Button>
                <Button
                    type="button"
                    onClick={goToAdmLogin}
                    textButton="login adm"
                    id="adm">
                </Button>
            </ButtonLoginContainer>
        </Container>
    )
};

FormSorteio.defaultProps = {

    namePlaceholderText: "Seu Nome",
    nameLabelText: "Nome",
    buttonText: "Enviar",
    titleText: "Cadastro",
    placeholderEmailText: "email@email.com",
    labelEmailText: "Email"
};

export default MapTo("sorteio/components/form-sorteio")(FormSorteio);