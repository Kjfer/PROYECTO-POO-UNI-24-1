-- =============================================
-- Seleccionar la base de datos
-- =============================================
use BIBLIOTECA
-- =============================================
-- Cargar Datos de Prueba
-- =============================================

-- Tabla: Guia
insert into Guia values('Faltas maximas',5)
insert into Guia values('Tiempo de devolucion en dias',5)
-- Tabla: TarifaPenalizaciones

insert into TarifaPenalizaciones values('LIMITE DE FALTAS','Pago por alcanzar el limite de faltas',15.00)
insert into TarifaPenalizaciones values('LIBRO DAÑADO','Pago significativo por el libro dañado',90.00)
insert into TarifaPenalizaciones values('PERDIDA DE LIBRO','Pago del precio del libro más un monto de mora',50)

-- Tabla: Faltas

insert into Faltas values(0,7,1,'La falta suma un valor unitario')
insert into Faltas values(7,21,2,'La falta suma un valor doble')
insert into Faltas values(21,35,3,'La falta suma un valor triple')
insert into Faltas values(35,1000,4,'La falta suma un valor cuadruple')

--Tabla: Empleados

insert into Empleados values('ADMINISTRADOR','Gary David','Fernandez Rojas','kjfer','gfer4548',convert(varchar(10),getdate(),103),'EN LABOR')
insert into Empleados values('ADMINISTRADOR','Frida Hazel','Guevara Sainoz','pirachachel','piris27',convert(varchar(10),getdate(),103),'EN LABOR')
insert into Empleados values('ADMINISTRADOR','Gustavo Adrian','Cerati Clark','gcsoda','mvv2007',convert(varchar(10),getdate(),103),'EN LABOR')
insert into Empleados values('BIBLIOTECARIO','Jose Julian','Cornejo Palomino','cpalomino','12563987',convert(varchar(10),getdate(),103),'EN LABOR')
insert into Empleados values('BIBLIOTECARIO','Kevin Alexis','Tapia Arevalo','ktapia','45622863',convert(varchar(10),getdate(),103),'EN LABOR')
insert into Empleados values('BIBLIOTECARIO','Pedro Pablo','Pascal Gutierrez','pguti15','23654863',convert(varchar(10),getdate(),103),'EN LABOR')
insert into Empleados values('BIBLIOTECARIO','Albert Einstein','Quispe Delgado','aedelgado15','1523653',convert(varchar(10),getdate(),103),'EN LABOR')
insert into Empleados values('BIBLIOTECARIO','Jose Paolo','Guerrero Gonzales','jpaolin9','15112017',convert(varchar(10),getdate(),103),'EN LABOR')

--Tabla: Alumnos

insert into Alumnos values('20221415A','Piero','Hurtado Acosta','piero.hurtado.a@uni.pe','phurta00','asddasj4',convert(varchar(10),getdate(),103),0,'ACTIVO')
insert into Alumnos values('20221518C','Xavi','Hernandez Huaman','xavi.hernandez.h@uni.pe','xher06','eyehfk7',convert(varchar(10),getdate(),103),0,'ACTIVO')
insert into Alumnos values('20212523D','Benjamin','Cueva Gavilan','benjamin.cueva.g@uni.pe','benjapro10','gggogo18',convert(varchar(10),getdate(),103),1,'ACTIVO')
insert into Alumnos values('20201526D','Laura Andrea','Gomez Perez','laura.gomez.p@uni.pe','lgomez45','noesta',convert(varchar(10),getdate(),103),0,'ACTIVO')
insert into Alumnos values('20231478Q','Regina','Ceballos Trossero','regina.ceballos.t@uni.pe','rceba07','kdosdko',convert(varchar(10),getdate(),103),0,'ACTIVO')
insert into Alumnos values('20201523R','Paulo','Dybala Estrada ','paulo.dybala.e@uni.pe','pdyb10','lajoya10',convert(varchar(10),getdate(),103),0,'ACTIVO')
insert into Alumnos values('20191025W','Jennifer','Lawrence Salgado','jennifer.lawrence.s@uni.pe','jenni1200','aieokfdm',convert(varchar(10),getdate(),103),0,'ACTIVO')
insert into Alumnos values('20241209Y','Sergio','Ramos Garcia','sergio.ramos.g@uni.pe','sramos4','20101253',convert(varchar(10),getdate(),103),3,'ACTIVO')
insert into Alumnos values('20234587M','Alexia','Flores Rosales','alexia.flores.r@uni.pe','aflor58','455d2d33',convert(varchar(10),getdate(),103),0,'ACTIVO')
insert into Alumnos values('20245127G','Jholvis','García Retuerto','jholvis.garcia.r@uni.pe','jgarcia777','1253260',convert(varchar(10),getdate(),103),2,'ACTIVO')
