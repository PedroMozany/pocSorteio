import React from 'react';
import { InputStyled, LabelStyled, InputContainerStyled } from './InputStyled';

export const Input = ({ label, placeholder, onChange, type, register, isError, ref, onBlur, onInput }) => {
    return (
        <InputContainerStyled isError={isError}>
            <LabelStyled>{label}              
            </LabelStyled>
            <InputStyled
                placeholder={placeholder}
                onChange = {onChange}
                onBlur = {onBlur}
                OnInput ={onInput}
                type={type}       
                ref={ref}
                {...register}        
            />                
        </InputContainerStyled>
    )
}