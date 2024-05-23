USE [master]
GO
/****** Object:  Database [BIBLIOTECA]    Script Date: 22/05/2024 19:57:15 ******/
CREATE DATABASE [BIBLIOTECA]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'BIBLIOTECA', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\BIBLIOTECA.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'BIBLIOTECA_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\BIBLIOTECA_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [BIBLIOTECA] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [BIBLIOTECA].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [BIBLIOTECA] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [BIBLIOTECA] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [BIBLIOTECA] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [BIBLIOTECA] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [BIBLIOTECA] SET ARITHABORT OFF 
GO
ALTER DATABASE [BIBLIOTECA] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [BIBLIOTECA] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [BIBLIOTECA] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [BIBLIOTECA] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [BIBLIOTECA] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [BIBLIOTECA] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [BIBLIOTECA] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [BIBLIOTECA] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [BIBLIOTECA] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [BIBLIOTECA] SET  ENABLE_BROKER 
GO
ALTER DATABASE [BIBLIOTECA] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [BIBLIOTECA] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [BIBLIOTECA] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [BIBLIOTECA] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [BIBLIOTECA] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [BIBLIOTECA] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [BIBLIOTECA] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [BIBLIOTECA] SET RECOVERY FULL 
GO
ALTER DATABASE [BIBLIOTECA] SET  MULTI_USER 
GO
ALTER DATABASE [BIBLIOTECA] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [BIBLIOTECA] SET DB_CHAINING OFF 
GO
ALTER DATABASE [BIBLIOTECA] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [BIBLIOTECA] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [BIBLIOTECA] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [BIBLIOTECA] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'BIBLIOTECA', N'ON'
GO
ALTER DATABASE [BIBLIOTECA] SET QUERY_STORE = ON
GO
ALTER DATABASE [BIBLIOTECA] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [BIBLIOTECA]
GO
/****** Object:  Table [dbo].[Devoluciones]    Script Date: 22/05/2024 19:57:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Devoluciones](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[PrestamoID] [int] NULL,
	[UsuarioID] [int] NULL,
	[EmpleadoID] [int] NULL,
	[EjemplarID] [varchar](12) NOT NULL,
	[FechaDevolucion] [date] NOT NULL,
	[TipoPrestamo] [varchar](100) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Ejemplares]    Script Date: 22/05/2024 19:57:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Ejemplares](
	[EjemplarID] [varchar](12) NOT NULL,
	[LibroID] [varchar](9) NOT NULL,
	[Estado] [varchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[EjemplarID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Empleados]    Script Date: 22/05/2024 19:57:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Empleados](
	[EmpleadoID] [int] IDENTITY(1,1) NOT NULL,
	[Nombre] [varchar](100) NOT NULL,
	[Apellido] [varchar](100) NOT NULL,
	[Email] [varchar](100) NOT NULL,
	[Contraseña] [varchar](100) NOT NULL,
	[FechaContratacion] [date] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[EmpleadoID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Faltas]    Script Date: 22/05/2024 19:57:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Faltas](
	[FaltaID] [int] IDENTITY(1,1) NOT NULL,
	[UsuarioID] [int] NULL,
	[FechaFalta] [date] NOT NULL,
	[Descripcion] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[FaltaID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Libros]    Script Date: 22/05/2024 19:57:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Libros](
	[LibroID] [varchar](9) NOT NULL,
	[Titulo] [varchar](255) NOT NULL,
	[Autor] [varchar](100) NOT NULL,
	[Categoria] [varchar](100) NOT NULL,
	[CDU] [varchar](10) NOT NULL,
	[AñoPublicacion] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[LibroID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Penalizaciones]    Script Date: 22/05/2024 19:57:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Penalizaciones](
	[PenalizacionID] [int] IDENTITY(1,1) NOT NULL,
	[UsuarioID] [int] NULL,
	[Monto] [decimal](10, 2) NOT NULL,
	[FechaPenalizacion] [date] NOT NULL,
	[Pagado] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[PenalizacionID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Prestamos]    Script Date: 22/05/2024 19:57:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Prestamos](
	[PrestamoID] [int] IDENTITY(1,1) NOT NULL,
	[UsuarioID] [int] NULL,
	[EmpleadoID] [int] NULL,
	[EjemplarID] [varchar](12) NOT NULL,
	[FechaPrestamo] [date] NOT NULL,
	[FechaDevolucion] [date] NOT NULL,
	[TipoPrestamo] [varchar](100) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[PrestamoID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Usuarios]    Script Date: 22/05/2024 19:57:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Usuarios](
	[UsuarioID] [int] IDENTITY(1,1) NOT NULL,
	[Nombre] [varchar](100) NOT NULL,
	[Apellido] [varchar](100) NOT NULL,
	[Email] [varchar](100) NOT NULL,
	[Contraseña] [varchar](100) NOT NULL,
	[FechaRegistro] [date] NOT NULL,
	[Faltas] [int] NULL,
	[Bloqueado] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[UsuarioID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Ejemplares] ADD  DEFAULT ('Disponible') FOR [Estado]
GO
ALTER TABLE [dbo].[Penalizaciones] ADD  DEFAULT ((0)) FOR [Pagado]
GO
ALTER TABLE [dbo].[Usuarios] ADD  DEFAULT ((0)) FOR [Faltas]
GO
ALTER TABLE [dbo].[Usuarios] ADD  DEFAULT ((0)) FOR [Bloqueado]
GO
ALTER TABLE [dbo].[Devoluciones]  WITH CHECK ADD FOREIGN KEY([EjemplarID])
REFERENCES [dbo].[Ejemplares] ([EjemplarID])
GO
ALTER TABLE [dbo].[Devoluciones]  WITH CHECK ADD FOREIGN KEY([EmpleadoID])
REFERENCES [dbo].[Empleados] ([EmpleadoID])
GO
ALTER TABLE [dbo].[Devoluciones]  WITH CHECK ADD FOREIGN KEY([PrestamoID])
REFERENCES [dbo].[Prestamos] ([PrestamoID])
GO
ALTER TABLE [dbo].[Devoluciones]  WITH CHECK ADD FOREIGN KEY([UsuarioID])
REFERENCES [dbo].[Usuarios] ([UsuarioID])
GO
ALTER TABLE [dbo].[Ejemplares]  WITH CHECK ADD FOREIGN KEY([LibroID])
REFERENCES [dbo].[Libros] ([LibroID])
GO
ALTER TABLE [dbo].[Faltas]  WITH CHECK ADD FOREIGN KEY([UsuarioID])
REFERENCES [dbo].[Usuarios] ([UsuarioID])
GO
ALTER TABLE [dbo].[Penalizaciones]  WITH CHECK ADD FOREIGN KEY([UsuarioID])
REFERENCES [dbo].[Usuarios] ([UsuarioID])
GO
ALTER TABLE [dbo].[Prestamos]  WITH CHECK ADD FOREIGN KEY([EjemplarID])
REFERENCES [dbo].[Ejemplares] ([EjemplarID])
GO
ALTER TABLE [dbo].[Prestamos]  WITH CHECK ADD FOREIGN KEY([EmpleadoID])
REFERENCES [dbo].[Empleados] ([EmpleadoID])
GO
ALTER TABLE [dbo].[Prestamos]  WITH CHECK ADD FOREIGN KEY([UsuarioID])
REFERENCES [dbo].[Usuarios] ([UsuarioID])
GO
/****** Object:  StoredProcedure [dbo].[sp_InsertarEjemplar]    Script Date: 22/05/2024 19:57:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_InsertarEjemplar]
    @LibroID VARCHAR(9)
AS
BEGIN
    DECLARE @EjemplarID VARCHAR(12);
    DECLARE @UltimoEjemplarID VARCHAR(12);
    DECLARE @NuevoNumero INT;

    -- Obtener el último EjemplarID insertado para el LibroID dado
    SELECT @UltimoEjemplarID = MAX(EjemplarID)
    FROM Ejemplares
    WHERE LibroID = @LibroID;

    IF @UltimoEjemplarID IS NULL
    BEGIN
        -- Si no hay ejemplares previos, empezar con 001
        SET @NuevoNumero = 1;
    END
    ELSE
    BEGIN
        -- Extraer la parte numérica del EjemplarID y convertirla a INT
        SET @NuevoNumero = CAST(RIGHT(@UltimoEjemplarID, 3) AS INT) + 1;
    END

    -- Construir el nuevo EjemplarID
    SET @EjemplarID = @LibroID + RIGHT('000' + CAST(@NuevoNumero AS VARCHAR(3)), 3);

    -- Insertar el nuevo ejemplar en la tabla Ejemplares
    INSERT INTO Ejemplares (EjemplarID, LibroID, Estado)
    VALUES (@EjemplarID, @LibroID, 'Disponible');
END;
GO
/****** Object:  StoredProcedure [dbo].[sp_InsertarLibro]    Script Date: 22/05/2024 19:57:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_InsertarLibro]
    @Titulo VARCHAR(255),
    @Autor VARCHAR(100),
    @Categoria VARCHAR(100),
    @CDU VARCHAR(10),
    @AñoPublicacion INT
AS
BEGIN
    DECLARE @LibroID VARCHAR(9);
    DECLARE @AutorIniciales VARCHAR(3);
    DECLARE @TituloParte VARCHAR(3);

    -- Obtener las primeras tres letras del nombre del autor
    SET @AutorIniciales = LEFT(@Autor, 3);

    -- Obtener tres letras del título del libro
    SET @TituloParte = LEFT(REPLACE(@Titulo, ' ', ''), 3);

    -- Si el título tiene menos de 3 caracteres, rellenar con 'X'
    IF LEN(@TituloParte) < 3
    BEGIN
        SET @TituloParte = RIGHT(CONCAT(@TituloParte, 'XXX'), 3);
    END

    -- Crear el LibroID combinando CDU, iniciales del autor y parte del título
    SET @LibroID = LEFT(@CDU, 3) + @AutorIniciales + @TituloParte;

    -- Insertar el nuevo libro en la tabla Libros
    INSERT INTO Libros (LibroID, Titulo, Autor, Categoria, CDU, AñoPublicacion)
    VALUES (@LibroID, @Titulo, @Autor, @Categoria, @CDU, @AñoPublicacion);
END;
GO
USE [master]
GO
ALTER DATABASE [BIBLIOTECA] SET  READ_WRITE 
GO
