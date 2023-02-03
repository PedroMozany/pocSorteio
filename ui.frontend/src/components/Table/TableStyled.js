import styled from "styled-components";
import { toRem } from "../../utils/ToRem";

export const TableStyled = styled.table`
display:flex;
flex-direction:column;
justify-content:space-around;
gap: ${toRem(16)};`

export const TheadStyled = styled.thead`
width:100%;
background:rgb(255,206,0);
color: #111213;
font-family: 'Mulish', sans-serif;
font-size: ${toRem(20)};
font-weight: 700;
`
export const TbodyStyled = styled.tbody`
width:100%;
height:100%;
background: #111213;
display: flex;
flex-direction:column;
font-family:'Mulish', sans-serif;


`
export const TrHeadStyled = styled.tr`
display:grid;
grid-template-columns: 1fr 1fr;
justify-items:left;
padding-left: ${toRem(8)};
color: #111213;
`