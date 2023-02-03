import styled from "styled-components";
import { toRem } from "../../../utils/ToRem";

export const TextStyled = styled.span`
font-family:'Mulish', sans-serif;
font-size: ${toRem(20)};
font-weight: 700;
color:#f8f9fc;
&.textTable{
font-size: ${toRem(18)};
font-weight: 500;
}
&.warning{ 
    font-size: ${toRem(10)};
    font-weight: 500;
    font-style: italic;
}
&.error{ 
    font-size: ${toRem(16)};
    font-weight: 500;
    font-style: italic;
    color:#FF8000;
}
`
export const VariableText = styled.p`
font-family:'Mulish', sans-serif;
color:rgb(255,206,0);
font-size: ${toRem(18)};
font-weight: 500;
`