package org.example.di;

import org.example.controller.*;
import org.example.repository.*;
import org.example.routes.*;
import org.example.service.*;


public class AppModule {
    // Usuario
    private static final UsuarioRepository usuarioRepository = new UsuarioRepository();
    private static final UsuarioService usuarioService = new UsuarioService(usuarioRepository);
    private static final UsuarioController usuarioController = new UsuarioController(usuarioService);

    // TipoReporte
    private static final TipoReporteRepository tipoReporteRepository = new TipoReporteRepository();
    private static final TipoReporteService tipoReporteService = new TipoReporteService(tipoReporteRepository);
    private static final TipoReporteController tipoReporteController = new TipoReporteController(tipoReporteService);

    //Secciones
    private static final SeccionRepository seccionRepository = new SeccionRepository();
    private static final SeccionService seccionService = new SeccionService(seccionRepository);
    private static final SeccionController seccionController = new SeccionController(seccionService);

    //Reportes
    private static final ReporteRepository reporteRepository = new ReporteRepository();
    private static final ReporteService reporteService = new ReporteService(reporteRepository);
    private static final ReporteController reporteController = new ReporteController(reporteService);

    //Nivel Urgencia
    private static final NivelUrgenciaRepository nivelUrgenciaRepository = new NivelUrgenciaRepository();
    private static final NivelUrgenciaService nivelUrgenciaService = new NivelUrgenciaService(nivelUrgenciaRepository);
    private static final NivelUrgenciaController nivelUrgenciaController = new NivelUrgenciaController(nivelUrgenciaService);

    //Mensajes Admin
    private static final MensajeAdminRepository mensajeAdminRepository = new MensajeAdminRepository();
    private static final MensajeAdminService mensajeAdminService = new MensajeAdminService(mensajeAdminRepository);
    private static final MensajeAdminController mensajeAdminController = new MensajeAdminController(mensajeAdminService);

    //Estado Reporte
    private static final EstadoReporteRepository estadoReporteRepository = new EstadoReporteRepository();
    private static final EstadoReporteService estadoReporteService = new EstadoReporteService(estadoReporteRepository);
    private static final EstadoReporteController estadoReporteController = new EstadoReporteController(estadoReporteService);

    //calles
    private static final CalleRepository calleRepository = new CalleRepository();
    private static final CalleService calleService = new CalleService(calleRepository);
    private static final CalleController calleController = new CalleController(calleService);

    //Ubicacion
    private static final UbicacionCalleSeccionRepository ubicacionRepo = new UbicacionCalleSeccionRepository();
    private static final UbicacionCalleSeccionService ubicacionService = new UbicacionCalleSeccionService(ubicacionRepo);
    private static final UbicacionCalleSeccionController ubicacionController = new UbicacionCalleSeccionController(ubicacionService);





    // Getters públicos para controladores
    public static UsuarioController getUsuarioController() {
        return usuarioController;
    }

    public static TipoReporteController getTipoReporteController() {
        return tipoReporteController;
    }

    public static SeccionController getSeccionController() {
        return seccionController;
    }

    public static ReporteController getReporteController() {
        return reporteController;
    }

    public static NivelUrgenciaController getNivelUrgenciaController() {
        return nivelUrgenciaController;
    }

    public static MensajeAdminController getMensajeAdminController() {
        return mensajeAdminController;
    }

    public static EstadoReporteController getEstadoReporteController() {
        return estadoReporteController;
    }

    public static CalleController getCalleController() {
        return calleController;
    }

    public static UbicacionCalleSeccionController getUbicacionCalleSeccionController() {return ubicacionController;}

}