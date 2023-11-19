import styled from "styled-components";
import { toRem } from "../../utils/ToRem";


export const SuccessContainer = styled.div`
display:flex;
flex-direction:column;
align-items:center;
gap: ${toRem(40)};
padding: ${toRem(24)};
border: ${toRem(1)} solid #f8f9fc;
margin: 0 ${toRem(80)} 0 ${toRem(80)};
background: #111213;
`