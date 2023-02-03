import React from 'react';
import { MapTo } from "@adobe/aem-react-editable-components";
import { Input } from '../micro/input/Input';
import { FormContainer } from './FormStyled';
import { InputContainer } from './FormStyled';
import { ButtonContainer } from './FormStyled';
import {Button} from '../Micro/Button/Button';


const Form = ({formContainer, buttonText}) => {

    return (
        <FormContainer>
            {formContainer.map(({placeholderText, labelText}, index) => (
                <InputContainer key={index}>
                    <Input
                        placeholder={placeholderText}
                        label={labelText}
                        type="text"                        
                    />
                </InputContainer>
            ))}
            <ButtonContainer>
                <Button
                    type="submit"                    
                    textButton={buttonText}
                />
            </ButtonContainer>


        </FormContainer>
    )
};

Form.defaultProps = {
    formContainer: [
        {
        placeholderText: "Insira o texto do placeholder",
        labelText: "Insira o texto da label",
    }
    ],
    buttonText:"Botão"
};

export default MapTo("sorteio/components/form-container")(Form);