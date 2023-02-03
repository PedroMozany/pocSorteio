import React from 'react';
import {TitleStyled} from './TitleStyled';

export const Title = ({text, id}) => {
    return (
        <TitleStyled
        id={id}
        className={`${id}`}>
        {text}</TitleStyled>       
    )
};