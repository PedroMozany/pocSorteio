import React from 'react';
import { TextStyled, VariableText } from './TextStyled';

export const Text = ({text, id, variable}) => { 
    return(
        <>
        <TextStyled
        id={id}
        className={`${id}`}>
        {text}</TextStyled>
        <VariableText>{variable}</VariableText>
        </>
    )
}

