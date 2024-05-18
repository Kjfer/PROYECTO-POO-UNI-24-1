USE [master]
GO
/****** Object:  Database [BibliotecaFIIS]    Script Date: 18/05/2024 13:11:40 ******/
CREATE DATABASE [BibliotecaFIIS]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'BibliotecaFIIS', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\BibliotecaFIIS.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'BibliotecaFIIS_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\BibliotecaFIIS_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [BibliotecaFIIS] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [BibliotecaFIIS].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [BibliotecaFIIS] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [BibliotecaFIIS] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [BibliotecaFIIS] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [BibliotecaFIIS] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [BibliotecaFIIS] SET ARITHABORT OFF 
GO
ALTER DATABASE [BibliotecaFIIS] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [BibliotecaFIIS] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [BibliotecaFIIS] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [BibliotecaFIIS] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [BibliotecaFIIS] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [BibliotecaFIIS] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [BibliotecaFIIS] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [BibliotecaFIIS] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [BibliotecaFIIS] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [BibliotecaFIIS] SET  ENABLE_BROKER 
GO
ALTER DATABASE [BibliotecaFIIS] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [BibliotecaFIIS] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [BibliotecaFIIS] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [BibliotecaFIIS] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [BibliotecaFIIS] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [BibliotecaFIIS] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [BibliotecaFIIS] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [BibliotecaFIIS] SET RECOVERY FULL 
GO
ALTER DATABASE [BibliotecaFIIS] SET  MULTI_USER 
GO
ALTER DATABASE [BibliotecaFIIS] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [BibliotecaFIIS] SET DB_CHAINING OFF 
GO
ALTER DATABASE [BibliotecaFIIS] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [BibliotecaFIIS] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [BibliotecaFIIS] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [BibliotecaFIIS] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'BibliotecaFIIS', N'ON'
GO
ALTER DATABASE [BibliotecaFIIS] SET QUERY_STORE = ON
GO
ALTER DATABASE [BibliotecaFIIS] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [BibliotecaFIIS]
GO
/****** Object:  Table [dbo].[empleado]    Script Date: 18/05/2024 13:11:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[empleado](
	[idempleado] [int] IDENTITY(1,1) NOT NULL,
	[apellido] [varchar](255) NULL,
	[clave] [varchar](255) NULL,
	[direccion] [varchar](255) NULL,
	[email] [varchar](255) NULL,
	[nombre] [varchar](255) NULL,
	[usuario] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[idempleado] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[historialprestamos]    Script Date: 18/05/2024 13:11:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[historialprestamos](
	[id_historial] [int] IDENTITY(1,1) NOT NULL,
	[fecha_devolucion] [datetime2](6) NULL,
	[fecha_prestamo] [datetime2](6) NULL,
	[id_libro] [int] NOT NULL,
	[id_usuario] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_historial] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[informelibros]    Script Date: 18/05/2024 13:11:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[informelibros](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[anio] [int] NOT NULL,
	[id_libro] [int] NOT NULL,
	[mes] [varchar](255) NULL,
	[prestamos_mes] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[informeusuarios]    Script Date: 18/05/2024 13:11:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[informeusuarios](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[anio] [int] NOT NULL,
	[id_usuario] [int] NOT NULL,
	[mes] [varchar](255) NULL,
	[prestamos_mes] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[libros]    Script Date: 18/05/2024 13:11:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[libros](
	[id_libro] [int] IDENTITY(1,1) NOT NULL,
	[anio_publicacion] [int] NOT NULL,
	[autor] [varchar](255) NULL,
	[genero] [varchar](255) NULL,
	[numero_ejemplares] [int] NOT NULL,
	[titulo] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_libro] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[prestamos]    Script Date: 18/05/2024 13:11:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[prestamos](
	[id_prestamo] [int] IDENTITY(1,1) NOT NULL,
	[estado] [varchar](255) NULL,
	[fecha_devolucion] [datetime2](6) NULL,
	[fecha_prestamo] [datetime2](6) NULL,
	[fecha_vencimiento] [datetime2](6) NULL,
	[id_empleado] [int] NOT NULL,
	[id_libro] [int] NOT NULL,
	[id_usuario] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_prestamo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[usuarios]    Script Date: 18/05/2024 13:11:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[usuarios](
	[id_usuario] [int] IDENTITY(1,1) NOT NULL,
	[apellido] [varchar](255) NULL,
	[clave] [varchar](255) NULL,
	[email] [varchar](255) NULL,
	[fecha_registro] [datetime2](6) NULL,
	[nombre] [varchar](255) NULL,
	[usuario] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_usuario] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[historialprestamos]  WITH CHECK ADD  CONSTRAINT [FK_historialprestamos_libros] FOREIGN KEY([id_libro])
REFERENCES [dbo].[libros] ([id_libro])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[historialprestamos] CHECK CONSTRAINT [FK_historialprestamos_libros]
GO
ALTER TABLE [dbo].[informelibros]  WITH CHECK ADD  CONSTRAINT [FK_informelibros_libros] FOREIGN KEY([id_libro])
REFERENCES [dbo].[libros] ([id_libro])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[informelibros] CHECK CONSTRAINT [FK_informelibros_libros]
GO
ALTER TABLE [dbo].[informeusuarios]  WITH CHECK ADD  CONSTRAINT [FK_informeusuarios_usuarios] FOREIGN KEY([id_usuario])
REFERENCES [dbo].[usuarios] ([id_usuario])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[informeusuarios] CHECK CONSTRAINT [FK_informeusuarios_usuarios]
GO
ALTER TABLE [dbo].[prestamos]  WITH CHECK ADD  CONSTRAINT [FK_prestamos_empleado] FOREIGN KEY([id_empleado])
REFERENCES [dbo].[empleado] ([idempleado])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[prestamos] CHECK CONSTRAINT [FK_prestamos_empleado]
GO
ALTER TABLE [dbo].[prestamos]  WITH CHECK ADD  CONSTRAINT [FK_prestamos_libros] FOREIGN KEY([id_libro])
REFERENCES [dbo].[libros] ([id_libro])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[prestamos] CHECK CONSTRAINT [FK_prestamos_libros]
GO
ALTER TABLE [dbo].[prestamos]  WITH CHECK ADD  CONSTRAINT [FK_prestamos_usuarios] FOREIGN KEY([id_usuario])
REFERENCES [dbo].[usuarios] ([id_usuario])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[prestamos] CHECK CONSTRAINT [FK_prestamos_usuarios]
GO
USE [master]
GO
ALTER DATABASE [BibliotecaFIIS] SET  READ_WRITE 
GO
