import React from 'react';
import { ButtonStyled } from './ButtonStyled';


export const Button = ({ type, onClick, textButton, id, onChange }) => {
    return (
        <ButtonStyled
            type={type}
            onClick={onClick}
            onChange={onChange}
            id={id}
            className={`${id}`}>
            {textButton}
        </ButtonStyled>

    )
};