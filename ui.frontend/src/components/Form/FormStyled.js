import styled from "styled-components";
import { toRem } from "../../utils/ToRem";


export const FormContainer = styled.form`
background: #eeeded;
width: 100%;
height: 100%;
margin: ${toRem(100)};
border-radius: ${toRem(32)};
`;

export const InputContainer = styled.div`
display:flex;
width: 100%;
`;

export const ButtonContainer = styled.div`
display:flex;
align-items: right;
width: 100%;
`;