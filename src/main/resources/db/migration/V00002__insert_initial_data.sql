INSERT INTO tb_categoria (id, nome) VALUES
('30000000-0000-0000-0000-000000000001', 'Desenvolvimento de Software'),
('30000000-0000-0000-0000-000000000002', 'DevOps e Deploy'),
('30000000-0000-0000-0000-000000000003', 'Git e Versionamento'),
('30000000-0000-0000-0000-000000000004', 'Código Legado e Refatoração'),
('30000000-0000-0000-0000-000000000005', 'Testes e Qualidade'),
('30000000-0000-0000-0000-000000000006', 'Infraestrutura e Containers'),
('30000000-0000-0000-0000-000000000007', 'Gestão Ágil'),
('30000000-0000-0000-0000-000000000008', 'Banco de Dados'),
('30000000-0000-0000-0000-000000000009', 'Carreira e Equipe');

INSERT INTO tb_post (id, autor, data, titulo, texto, categoria_id) VALUES

('10000000-0000-0000-0000-000000000001',
 'Albert Einstein',
 '2026-10-01',
 'Deploy na sexta-feira é relativo',
 'Einstein descobriu que o tempo é relativo. O desenvolvedor descobriu que 5 minutos antes do deploy duram uma eternidade e 5 minutos depois do deploy passam em velocidade de produção.',
 '30000000-0000-0000-0000-000000000002'),

('10000000-0000-0000-0000-000000000002',
 'Sigmund Freud',
 '2026-10-01',
 'Git: o inconsciente do desenvolvedor',
 'Freud estudou o inconsciente humano. Se estudasse um repositório Git, descobriria que todo desenvolvedor possui pelo menos um commit chamado "ajustes finais", feito às 03:47 da manhã.',
 '30000000-0000-0000-0000-000000000003'),

('10000000-0000-0000-0000-000000000003',
 'Carl Jung',
 '2026-10-01',
 'A sombra do código legado',
 'Jung estudou a sombra psicológica. O desenvolvedor conhece outra sombra: o módulo legado que ninguém entende, ninguém documentou e ninguém tem coragem de apagar.',
 '30000000-0000-0000-0000-000000000004'),

('10000000-0000-0000-0000-000000000004',
 'Alan Turing',
 '2026-10-01',
 'O teste de Turing encontrou um bug',
 'Turing queria descobrir se uma máquina conseguia parecer humana. Hoje bastaria perguntar: "Funciona na sua máquina?" Se a resposta for "sim", temos evidências suficientes.',
 '30000000-0000-0000-0000-000000000005'),

('10000000-0000-0000-0000-000000000005',
 'Ada Lovelace',
 '2026-10-01',
 'Ada encontra um bug moderno',
 'Ada escreveu algoritmos quando quase ninguém imaginava o futuro da programação. Séculos depois, ela abriu um projeto Java e encontrou 47 dependências, 13 warnings e um NullPointerException.',
 '30000000-0000-0000-0000-000000000001'),

('10000000-0000-0000-0000-000000000006',
 'Nikola Tesla',
 '2026-10-01',
 'Docker funciona... mas no meu computador',
 'Tesla queria transmitir energia sem fios. O desenvolvedor moderno quer transmitir a mesma aplicação entre ambientes sem ouvir: "Aqui funciona no meu Docker."',
 '30000000-0000-0000-0000-000000000006'),

('10000000-0000-0000-0000-000000000007',
 'Isaac Newton',
 '2026-10-01',
 'A terceira lei do deploy',
 'Newton descobriu que toda ação possui uma reação. No DevOps é simples: você faz deploy e o Slack imediatamente reage com uma mensagem dizendo que produção está fora do ar.',
 '30000000-0000-0000-0000-000000000002'),

('10000000-0000-0000-0000-000000000008',
 'Sócrates',
 '2026-10-01',
 'Só sei que meu código funciona',
 'Sócrates dizia que reconhecer a própria ignorância era sabedoria. O desenvolvedor moderno atualizou: "Só sei que funciona. Não me pergunte por quê."',
 '30000000-0000-0000-0000-000000000001'),

('10000000-0000-0000-0000-000000000009',
 'Galileu Galilei',
 '2026-10-01',
 'Eppur si deploya',
 'Galileu enfrentou críticas por suas ideias. O desenvolvedor enfrenta o mesmo problema quando diz que o código está correto enquanto o Kubernetes reinicia o pod pela décima vez.',
 '30000000-0000-0000-0000-000000000006'),

('10000000-0000-0000-0000-000000000010',
 'Aristóteles',
 '2026-10-01',
 'A essência de um projeto legado',
 'Aristóteles queria descobrir a essência das coisas. O desenvolvedor abriu um projeto legado e descobriu a essência: ninguém sabe como funciona, mas se você remover uma linha, três sistemas quebram.',
 '30000000-0000-0000-0000-000000000004'),

('10000000-0000-0000-0000-000000000011',
 'Pôncio Pilatos',
 '2026-10-01',
 'Lavo minhas mãos: deploy de sexta às 17:59',
 'Pilatos lavou as mãos e foi embora. O dev também: aperta o deploy às 17:59, lava as mãos, desliga o celular e vai embora. Se alguém ligar, a bateria acabou. Coincidência. Todo fim de semana.',
 '30000000-0000-0000-0000-000000000002'),

('10000000-0000-0000-0000-000000000012',
 'Júlio César',
 '2026-10-01',
 'Até tu, Brutus? Aprovou meu PR sem ler',
 'César foi apunhalado 23 vezes pelo Senado. O dev recebe 23 comentários no code review, todos começando com "nit:", e um "LGTM" do Brutus, que nem abriu o arquivo.',
 '30000000-0000-0000-0000-000000000003'),

('10000000-0000-0000-0000-000000000013',
 'Cleópatra',
 '2026-10-01',
 'Ctrl+Shift+R: a fé do desenvolvedor',
 'Cleópatra se enrolou num tapete para entrar no palácio. O dev se enrola no Ctrl+Shift+R para ver se o bug era cache. Não era. Mas ele tenta de novo, só por fé.',
 '30000000-0000-0000-0000-000000000001'),

('10000000-0000-0000-0000-000000000014',
 'Cristóvão Colombo',
 '2026-10-01',
 'Funciona como esperado (era a América)',
 'Colombo saiu para a Índia, chegou na América e disse: "funciona como esperado". Foi o primeiro a fazer deploy no ambiente errado e chamar de feature.',
 '30000000-0000-0000-0000-000000000002'),

('10000000-0000-0000-0000-000000000015',
 'Napoleão Bonaparte',
 '2026-10-01',
 'git push --force: a campanha da Rússia',
 'Napoleão invadiu a Rússia no inverno sem plano B. O dev dá git push --force na main na sexta sem plano B. Os dois voltaram com 10% do exército e muita explicação para dar.',
 '30000000-0000-0000-0000-000000000003'),

('10000000-0000-0000-0000-000000000016',
 'Diógenes',
 '2026-10-01',
 'Sai da frente da minha sprint',
 'Alexandre perguntou a Diógenes o que ele queria. Resposta: "que saia da frente do meu sol". O dev, quando o PM pergunta se pode ajudar: "que saia da frente da minha sprint".',
 '30000000-0000-0000-0000-000000000007'),

('10000000-0000-0000-0000-000000000017',
 'Leonardo da Vinci',
 '2026-10-01',
 'TODO: remover gambiarra depois (escrito em 2019)',
 'Leonardo deixou a Mona Lisa "quase pronta" por 16 anos. No código, o TODO: remover gambiarra depois está em produção desde 2019 e já virou patrimônio histórico da empresa.',
 '30000000-0000-0000-0000-000000000004'),

('10000000-0000-0000-0000-000000000018',
 'Hércules',
 '2026-10-01',
 'Os 12 trabalhos: o backlog do Jira',
 'Hércules matou a Hidra: cortava uma cabeça e nasciam duas. O dev fecha um ticket no Jira e nascem dois: "refatorar", "documentar" e "lembrar de documentar".',
 '30000000-0000-0000-0000-000000000007'),

('10000000-0000-0000-0000-000000000019',
 'Zeus',
 '2026-10-01',
 'Zeus e o DELETE sem WHERE',
 'Zeus lança raios sem pedir aprovação de ninguém. O dev lança DELETE FROM tb_cliente sem WHERE e comenta "ops". O Olimpo inteiro ouviu o trovão. O financeiro também.',
 '30000000-0000-0000-0000-000000000008'),

('10000000-0000-0000-0000-000000000020',
 'Nicolau Maquiavel',
 '2026-10-01',
 'O Príncipe e o estagiário',
 'Maquiavel dizia que é melhor ser temido do que amado. O dev sênior diz que é melhor ter um estagiário. Quando o sistema cai, foi ele. Quando funciona, foi o sênior. Quando o sênior sai, o estagiário vira sênior.',
 '30000000-0000-0000-0000-000000000009');

INSERT INTO tb_comment (id, autor, data, texto, post_id) VALUES

('20000000-0000-0000-0000-000000000001',
 'Sigmund Freud',
 '2026-10-01',
 'O desenvolvedor diz que está tranquilo. O subconsciente já abriu o rollback.',
 '10000000-0000-0000-0000-000000000001'),

('20000000-0000-0000-0000-000000000002',
 'Carl Jung',
 '2026-10-01',
 'Todo desenvolvedor possui uma sombra. Normalmente ela aparece no Jenkins.',
 '10000000-0000-0000-0000-000000000001'),

('20000000-0000-0000-0000-000000000003',
 'Albert Einstein',
 '2026-10-01',
 'O tempo é relativo. Principalmente quando o pipeline está em 99%.',
 '10000000-0000-0000-0000-000000000001'),

('20000000-0000-0000-0000-000000000004',
 'Nikola Tesla',
 '2026-10-01',
 'Eu queria energia sem fios. O desenvolvedor quer Wi-Fi no servidor e zero incidentes.',
 '10000000-0000-0000-0000-000000000001'),

('20000000-0000-0000-0000-000000000005',
 'Alan Turing',
 '2026-10-01',
 'Se o sistema responde 200, pergunte novamente. Pode ser apenas sorte.',
 '10000000-0000-0000-0000-000000000001'),

('20000000-0000-0000-0000-000000000006',
 'Isaac Newton',
 '2026-10-01',
 'Toda ação possui uma reação. Todo deploy possui uma mensagem no Slack.',
 '10000000-0000-0000-0000-000000000001'),

('20000000-0000-0000-0000-000000000007',
 'Sócrates',
 '2026-10-01',
 'Antes de fazer deploy, faça uma pergunta: quem vai ficar de plantão?',
 '10000000-0000-0000-0000-000000000001'),

('20000000-0000-0000-0000-000000000008',
 'Galileu Galilei',
 '2026-10-01',
 'Eppur o pod reinicia.',
 '10000000-0000-0000-0000-000000000001'),

('20000000-0000-0000-0000-000000000009',
 'Aristóteles',
 '2026-10-01',
 'A causa do incidente foi identificada: "é só uma alteração pequena".',
 '10000000-0000-0000-0000-000000000001'),

('20000000-0000-0000-0000-000000000010',
 'Ada Lovelace',
 '2026-10-01',
 'Sexta-feira, 17:59. O verdadeiro teste de resiliência começou.',
 '10000000-0000-0000-0000-000000000001'),

('20000000-0000-0000-0000-000000000011','Júlio César','2026-10-01','Até tu, Pilatos? Você já tinha sumido quando o pipeline ficou vermelho.','10000000-0000-0000-0000-000000000011'),
('20000000-0000-0000-0000-000000000012','Dom Pedro I','2026-10-01','Independência ou morte! Mas só até sexta às 17h. Depois eu também sumo.','10000000-0000-0000-0000-000000000011'),
('20000000-0000-0000-0000-000000000013','Sócrates','2026-10-01','Só sei que ninguém atende. A bateria acabou, né?','10000000-0000-0000-0000-000000000011'),

('20000000-0000-0000-0000-000000000014','Nicolau Maquiavel','2026-10-01','Aprovar sem ler é política. Já aprovei PR de 4 mil linhas olhando só o título.','10000000-0000-0000-0000-000000000012'),
('20000000-0000-0000-0000-000000000015','Sigmund Freud','2026-10-01','Você não quer code review. Quer alguém que diga que está tudo bem.','10000000-0000-0000-0000-000000000012'),
('20000000-0000-0000-0000-000000000016','Pôncio Pilatos','2026-10-01','LGTM. Lavo minhas mãos.','10000000-0000-0000-0000-000000000012'),

('20000000-0000-0000-0000-000000000017','Isaac Newton','2026-10-01','Ctrl+Shift+R: a terceira lei do desespero.','10000000-0000-0000-0000-000000000013'),
('20000000-0000-0000-0000-000000000018','Albert Einstein','2026-10-01','Insanidade é limpar o cache esperando um resultado diferente.','10000000-0000-0000-0000-000000000013'),
('20000000-0000-0000-0000-000000000019','Nikola Tesla','2026-10-01','Limpei o cache, o histórico e a sala. Nada.','10000000-0000-0000-0000-000000000013'),

('20000000-0000-0000-0000-000000000020','Pedro Álvares Cabral','2026-10-01','Também achei meu destino por acidente. Chamei de roadmap.','10000000-0000-0000-0000-000000000014'),
('20000000-0000-0000-0000-000000000021','Arquimedes','2026-10-01','Eureka! Era o ambiente errado. De novo.','10000000-0000-0000-0000-000000000014'),
('20000000-0000-0000-0000-000000000022','Alan Turing','2026-10-01','A saída é inesperada, mas o programa é válido.','10000000-0000-0000-0000-000000000014'),

('20000000-0000-0000-0000-000000000023','Júlio César','2026-10-01','Atravessei o Rubicão sem plano B. Alea jacta est. A branch protection estava desligada.','10000000-0000-0000-0000-000000000015'),
('20000000-0000-0000-0000-000000000024','Aristóteles','2026-10-01','A virtude está no meio: nem push --force, nem 14 aprovações.','10000000-0000-0000-0000-000000000015'),
('20000000-0000-0000-0000-000000000025','Hércules','2026-10-01','Deu conflito no merge. Usei meus 12 trabalhos e escolhi o git checkout --theirs.','10000000-0000-0000-0000-000000000015'),

('20000000-0000-0000-0000-000000000026','Alexandre, o Grande','2026-10-01','Saí da frente. A sprint continuou atrasada do mesmo jeito.','10000000-0000-0000-0000-000000000016'),
('20000000-0000-0000-0000-000000000027','Sócrates','2026-10-01','Só sei que a sprint não é minha.','10000000-0000-0000-0000-000000000016'),
('20000000-0000-0000-0000-000000000028','Pitágoras','2026-10-01','A hipotenusa da reunião de alinhamento é sempre maior que a soma dos catetos úteis.','10000000-0000-0000-0000-000000000016'),

('20000000-0000-0000-0000-000000000029','Sigmund Freud','2026-10-01','Esse TODO de 2019 é seu inconsciente pedindo ajuda. Ou esquecimento.','10000000-0000-0000-0000-000000000017'),
('20000000-0000-0000-0000-000000000030','Ada Lovelace','2026-10-01','Escrevi "temporário" em 1843. Ainda roda.','10000000-0000-0000-0000-000000000017'),
('20000000-0000-0000-0000-000000000031','Cleópatra','2026-10-01','Gambiarra com mais de 5 anos é patrimônio histórico.','10000000-0000-0000-0000-000000000017'),

('20000000-0000-0000-0000-000000000032','Júlio César','2026-10-01','Veni, vidi, abri outro ticket.','10000000-0000-0000-0000-000000000018'),
('20000000-0000-0000-0000-000000000033','Galileu Galilei','2026-10-01','Eppur o backlog cresce.','10000000-0000-0000-0000-000000000018'),
('20000000-0000-0000-0000-000000000034','Dom Pedro I','2026-10-01','Fico! Mas só se o ticket for para a próxima sprint.','10000000-0000-0000-0000-000000000018'),

('20000000-0000-0000-0000-000000000035','Pôncio Pilatos','2026-10-01','Não fui eu. Lavei as mãos antes do commit.','10000000-0000-0000-0000-000000000019'),
('20000000-0000-0000-0000-000000000036','Carl Jung','2026-10-01','O arquétipo do dev com acesso root: o Mago, que apaga a tabela errada.','10000000-0000-0000-0000-000000000019'),
('20000000-0000-0000-0000-000000000037','Cristóvão Colombo','2026-10-01','Conectei no banco errado e descobri produção. Parece familiar.','10000000-0000-0000-0000-000000000019'),

('20000000-0000-0000-0000-000000000038','Cleópatra','2026-10-01','Mandei o estagiário buscar café. Mandei o rollback junto.','10000000-0000-0000-0000-000000000020'),
('20000000-0000-0000-0000-000000000039','Diógenes','2026-10-01','Estagiário é feliz: mora num barril e ainda ganha café.','10000000-0000-0000-0000-000000000020'),
('20000000-0000-0000-0000-000000000040','Dom Pedro I','2026-10-01','Às margens do Ipiranga ouvi: o erro foi no ambiente de homologação.','10000000-0000-0000-0000-000000000020'),

('20000000-0000-0000-0000-000000000041','Arquimedes','2026-10-01','Eureka! Achei o commit certo. Chamava "ajustes finais v7".','10000000-0000-0000-0000-000000000002'),
('20000000-0000-0000-0000-000000000042','Zeus','2026-10-01','Eu fulminaria esse módulo legado, mas o raio precisa de aprovação do arquiteto.','10000000-0000-0000-0000-000000000003'),
('20000000-0000-0000-0000-000000000043','Pôncio Pilatos','2026-10-01','Na minha máquina funciona. Do resto eu lavo as mãos.','10000000-0000-0000-0000-000000000004'),
('20000000-0000-0000-0000-000000000044','Cristóvão Colombo','2026-10-01','Dependência certa, versão errada. Cheguei na Índia de novo.','10000000-0000-0000-0000-000000000005'),
('20000000-0000-0000-0000-000000000045','Diógenes','2026-10-01','Funciona no meu barril. Pode fechar o chamado.','10000000-0000-0000-0000-000000000006'),
('20000000-0000-0000-0000-000000000046','Napoleão Bonaparte','2026-10-01','Toda ação gera reação. Todo force push gera Waterloo.','10000000-0000-0000-0000-000000000007'),
('20000000-0000-0000-0000-000000000047','Nicolau Maquiavel','2026-10-01','Se funciona, assumo o mérito. Se quebra, foi o estagiário.','10000000-0000-0000-0000-000000000008'),
('20000000-0000-0000-0000-000000000048','Pedro Álvares Cabral','2026-10-01','Descobri esse pod por acaso. Ele ia reiniciar em outra direção.','10000000-0000-0000-0000-000000000009'),
('20000000-0000-0000-0000-000000000049','Leonardo da Vinci','2026-10-01','Posso refatorar. Entrego em quatro anos, com desenho e tudo.','10000000-0000-0000-0000-000000000010');

INSERT INTO role (name) VALUES
    ('ROLE_USER'),
    ('ROLE_ADMIN');

INSERT INTO app_user (username, password, full_name, enabled) VALUES
    ('admin',    '$2a$10$r.onnlwQHsdFcNZSOu7ZYOvkc5i9o8MVlpiaU9U6MxCWyon0eXXzK', 'Administrator', TRUE),
    ('student1', '$2a$10$r.onnlwQHsdFcNZSOu7ZYOvkc5i9o8MVlpiaU9U6MxCWyon0eXXzK', 'Student One',  TRUE);