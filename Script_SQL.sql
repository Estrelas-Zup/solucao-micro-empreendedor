-- ADICIONAR PRODUTO

INSERT INTO `smei`.`produto` (`margem_desconto`, `nome`, `unidade_medida`, `valor_custo`, `valor_venda`) VALUES ('5', 'Pao de queijo', 'UNIDADE', '0.80', '1.50');
INSERT INTO `smei`.`produto` (`margem_desconto`, `nome`, `unidade_medida`, `valor_custo`, `valor_venda`) VALUES ('2', 'Pao de sal', 'UNIDADE', '0.45', '0.60');


-- ADICIONAR ESTOQUE
INSERT INTO `smei`.`estoque` (`data_validade`, `disponibilidade`, `perda`, `quantidade`, `id_produto`) VALUES ('2020-11-25', '1', '0', '10', '1');
INSERT INTO `smei`.`estoque` (`data_validade`, `disponibilidade`, `perda`, `quantidade`, `id_produto`) VALUES ('2020-11-26', '0', '0', '5', '1');
INSERT INTO `smei`.`estoque` (`data_validade`, `disponibilidade`, `perda`, `quantidade`, `id_produto`) VALUES ('2020-11-26', '1', '0', '10', '2');
INSERT INTO `smei`.`estoque` (`data_validade`, `disponibilidade`, `perda`, `quantidade`, `id_produto`) VALUES ('2020-11-25', '0', '0', '10', '1');