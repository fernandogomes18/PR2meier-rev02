drop database hospital;
Create database hospital ;
Use hospital;
Create table cadmedico(
      crm varchar (20) not null,
     nome varchar(40) not null, 
     tel  varchar (20), 
     especialidade longtext,
     primary key (crm)); 
     


Create table cadpaciente(
      cpf varchar (20) not null,
     nome varchar(40) not null, 
     tel  varchar (20), 
     endereco  longtext,
     primary key (CPF)); 


create table ag_consulta(
       id_con int(11) not null auto_increment,
       crm_med  varchar(20) not null,
       cpf_paci varchar (20) not null,
       data_con date,
       primary key(id_con),
       foreign key (crm_med) references cadmedico (crm) on delete cascade on update cascade,
       foreign key (cpf_paci) references cadpaciente (cpf) on delete cascade on update cascade);   


       
Insert into cadmedico (crm, nome, tel, especialidade) values('123456tr','Maria','2126847712','Cardiologia');
       
Insert into cadmedico values('567865tr','Pedro','21-997651113','Obstetra');


insert into ag_consulta values('','4444444444','102.102.102-10','2017-10-10');