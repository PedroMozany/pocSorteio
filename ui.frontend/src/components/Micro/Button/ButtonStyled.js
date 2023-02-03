import styled from "styled-components";
import { toRem } from "../../../utils/ToRem";


export const ButtonStyled = styled.button`
font-family: 'Mulish', sans-serif;
font-weight:700;
font-size: ${toRem(24)};
max-width:100%;
max-height: 100%;
padding: ${toRem(16)};
color:#f8f9fc;
background: rgb(17, 18, 19);
border: ${toRem(1)} solid rgb(255,206,0);
cursor: pointer;
&:hover{
    background-color: rgb(255,206,0);
    color:#111213;
}
&.adm{
    background: transparent;
    color:rgb(255,206,0);
    font-size: ${toRem(16)};
    max-height: ${toRem(30)};
    border:none;
    &:hover{
        color:#f8f9fc;
    }
}
&.delete{
    color:#FF0024;
    background:rgb(17, 18, 19);
    max-height: ${toRem(30)};
    font-size: ${toRem(16)};
    border:none;
    &:hover{
        color:#FF8000;
    }
}
`