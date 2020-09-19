
    create table Categoria_entidades (
        id bigint not null auto_increment,
        texto varchar(255),
        primary key (id)
    )

    create table Comportamiento (
        DTYPE varchar(31) not null,
        id bigint not null auto_increment,
        montoMaximoEgresos decimal(19,2),
        entidadALaQueNoPuedePertenecer_id bigint,
        primary key (id)
    )

    create table Comportamiento_aplicado (
        id_categoria bigint not null,
        id_comportamiento bigint not null
    )

    create table DocumentoComercial (
        id bigint not null auto_increment,
        tipoDoc varchar(255),
        primary key (id)
    )

    create table Entidad (
        id bigint not null auto_increment,
        nombreFicticio varchar(255),
        categoriaEntidad_id bigint,
        id_organizacion bigint,
        primary key (id)
    )

    create table Entidades_Base (
        descripcion varchar(255),
        id bigint not null,
        entidadJuridica_id bigint,
        primary key (id)
    )

    create table Entidades_juridicas (
        categoriaEntidadJuridica varchar(255),
        codInscIGJ integer not null,
        cuit integer not null,
        direccionPostal varchar(255),
        razonSocial varchar(255),
        id bigint not null,
        primary key (id)
    )

    create table Items (
        id bigint not null auto_increment,
        descripcion varchar(255),
        currency varchar(255),
        monto decimal(19,2),
        presupuesto_asociado bigint,
        primary key (id)
    )

    create table Operaciones_De_Egreso (
        id bigint not null auto_increment,
        fechaOperacion tinyblob,
        presupuestosMinimos integer not null,
        presupuestoElegido_id bigint,
        Entidad bigint,
        primary key (id)
    )

    create table Organizaciones (
        id bigint not null auto_increment,
        descripcion varchar(255),
        primary key (id)
    )

    create table Presupuestos (
        id bigint not null auto_increment,
        documentoComercial_id bigint,
        presupuesto_proveedor bigint,
        operacion_asociada bigint,
        primary key (id)
    )

    create table Proveedores (
        id bigint not null auto_increment,
        cuil integer not null,
        altura varchar(255),
        calle varchar(255),
        depto varchar(255),
        piso varchar(255),
        ciudad varchar(255),
        pais varchar(255),
        provincia varchar(255),
        razonSocial varchar(255),
        primary key (id)
    )

    create table Revisor (
        id_operacion_de_egreso bigint not null,
        id_usuario bigint not null
    )

    create table Usuarios (
        id bigint not null auto_increment,
        hashedPasswordActual varchar(255),
        saltActual varchar(255),
        tipoUser varchar(255),
        username varchar(255),
        primary key (id)
    )

    alter table Comportamiento 
        add constraint FK_ap7bnkacfu6sgepdbywghmibo 
        foreign key (entidadALaQueNoPuedePertenecer_id) 
        references Entidades_juridicas (id)

    alter table Comportamiento_aplicado 
        add constraint FK_5h2s7idq7b7dgvykmguy3ycka 
        foreign key (id_comportamiento) 
        references Comportamiento (id)

    alter table Comportamiento_aplicado 
        add constraint FK_p17pkn2ifjd8d1ekptqxyu1ni 
        foreign key (id_categoria) 
        references Categoria_entidades (id)

    alter table Entidad 
        add constraint FK_tggvnsgyc0uno373ejqvrlb6x 
        foreign key (categoriaEntidad_id) 
        references Categoria_entidades (id)

    alter table Entidad 
        add constraint FK_ouswaln7ldegjbp1xn6qft1t3 
        foreign key (id_organizacion) 
        references Organizaciones (id)

    alter table Entidades_Base 
        add constraint FK_jwp7k4m5i9h0x3gem8fkoa5ai 
        foreign key (entidadJuridica_id) 
        references Entidades_juridicas (id)

    alter table Entidades_Base 
        add constraint FK_av55j1h7ko1i4ykl7efuh9ubg 
        foreign key (id) 
        references Entidad (id)

    alter table Entidades_juridicas 
        add constraint FK_n6dwhim2awufuhydpjnuf1tbv 
        foreign key (id) 
        references Entidad (id)

    alter table Items 
        add constraint FK_spcamspq2fu9r0xnx4y8qlrd4 
        foreign key (presupuesto_asociado) 
        references Presupuestos (id)

    alter table Operaciones_De_Egreso 
        add constraint FK_f9qb23w84yw0r97uu6fjh9xie 
        foreign key (presupuestoElegido_id) 
        references Presupuestos (id)

    alter table Operaciones_De_Egreso 
        add constraint FK_q4nsmvvbut8ceaeog2o7qlp9x 
        foreign key (Entidad) 
        references Entidad (id)

    alter table Presupuestos 
        add constraint FK_5w17c1u3jf6tbkf9phukiive0 
        foreign key (documentoComercial_id) 
        references DocumentoComercial (id)

    alter table Presupuestos 
        add constraint FK_5w1hc267jjaqchfgtb1g4dibx 
        foreign key (presupuesto_proveedor) 
        references Proveedores (id)

    alter table Presupuestos 
        add constraint FK_denomv39th35f99sa18fwvlti 
        foreign key (operacion_asociada) 
        references Operaciones_De_Egreso (id)

    alter table Revisor 
        add constraint FK_myyrk6fwd2u1ktw1fi0t8rpkw 
        foreign key (id_usuario) 
        references Usuarios (id)

    alter table Revisor 
        add constraint FK_cqrb32aton3jpc4lby0nyov71 
        foreign key (id_operacion_de_egreso) 
        references Operaciones_De_Egreso (id)
