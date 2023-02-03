import React from 'react';
import { TableStyled, TheadStyled, TrHeadStyled, TbodyStyled } from './TableStyled';

const Table = ({list}) => {
    return (
        <TableStyled>
            <TheadStyled>
                <TrHeadStyled>
                    <>
                        <th>Nome</th>
                    </>
                    <>
                        <th>Email</th>
                    </>
                </TrHeadStyled>
            </TheadStyled>
            <TbodyStyled>
                {list}                
            </TbodyStyled>
        </TableStyled>
    )
}

export default Table