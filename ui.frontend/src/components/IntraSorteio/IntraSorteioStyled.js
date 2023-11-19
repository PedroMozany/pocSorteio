import styled from "styled-components";
import { toRem } from "../../utils/ToRem";

export const SorteioContainer = styled.form`
display: flex;
flex-direction:column;
justify-content:space-between;
padding: ${toRem(32)};
background: #111213;
border: ${toRem(1)} solid #f8f9fc;
`;

export const CardStyled = styled.div`
gap: ${toRem(24)};
margin-left: ${toRem(40)};
`;

export const CadastrosContainer = styled.div`
display:flex;
flex-direction:column;
padding:${toRem(40)};
border: ${toRem(1)} solid #f8f9fc;
background: rgb(17, 18, 19);
`;

export const TrStyled = styled.tr`
font-family:'Mulish', sans-serif;
justify-items:left;
padding-left: ${toRem(8)};
font-size: ${toRem(18)};
font-weight: 500;
color:#f8f9fc;
`
export const PostsContainer = styled.div`
    display: flex;
    justify-content: space-between;
    font-family: 'Mulish', sans-serif;
    font-size: ${toRem(18)};
    font-weight: 500;
    color:#f8f9fc;
`
export const TitleContainer = styled.div`
display: flex;
justify-content: space-between;
align-items: center;
`

export const DeleteContainer = styled.div`
display: flex;
flex-direction: column;
gap: ${toRem(8)};
width: 20%;
`