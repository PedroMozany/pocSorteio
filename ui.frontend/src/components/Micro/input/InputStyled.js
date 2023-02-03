import styled from "styled-components";
import { toRem } from "../../../utils/ToRem";


export const InputContainerStyled = styled.div`
font-family: 'Mulish', sans-serif;
display: flex;
flex-direction: column;
`;

export const LabelStyled = styled.label`
font-family: 'Mulish', sans-serif;
margin-bottom: ${toRem(8)};
font-size:${toRem(18)};
font-weight:700;
color:#f8f9fc;
`;

export const InputStyled = styled.input`
font-family: 'Mulish', sans-serif;
height: ${toRem(32)};
max-width: 100%;
font-size:${toRem(16)};
font-weight: 600;
background: #f8f9fc;
color:#111213;
&:focus {
    outline: none !important;    
    border:${toRem(2)} solid rgb(255,206,0);
}
&::placeholder{
    color:#111213;
    padding-left: ${toRem(8)}
}
`;

