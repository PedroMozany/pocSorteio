import styled from "styled-components";
import { toRem } from "../../utils/ToRem";

export const Container = styled.div`
padding: ${toRem(64)};
border: ${toRem(1)} solid #f8f9fc;
margin: 0 ${toRem(80)} 0 ${toRem(80)};
background: #111213;
`

export const TitleContainer = styled.div`
margin-bottom: ${toRem(40)};
`

export const FormContainer = styled.form`
display:flex;
flex-direction:column;
gap: ${toRem(16)};
`;

export const ButtonContainer = styled.div`
display:flex;
justify-content:flex-end;
margin-top: ${toRem(24)};
`;

export const ErrorStyled = styled.span`
font-family: 'Mulish', sans-serif;
font-size: 1rem;
color:  rgb(255,206,0);
font-weight: 600;
`
export const ErrorContainer = styled.div`
height: ${toRem(32)};
`
export const  BtAdmContainer = styled.div`
display:flex;
justify-content:center;
margin-top: ${toRem(32)};
`