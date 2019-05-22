package co.com.ceiba.ceibaestacionamiento.dominio;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.com.ceiba.ceibaestacionamiento.dominio.servicios.CostoEstadiaServicio;
import co.com.ceiba.ceibaestacionamiento.dominio.servicios.FacturaServicio;
import co.com.ceiba.ceibaestacionamiento.dominio.servicios.MovilServicio;
import co.com.ceiba.ceibaestacionamiento.servicios.excepciones.MovilNoRegistradoException;
import co.com.ceiba.ceibaestacionamiento.servicios.excepciones.MovilRegistradoException;
import co.com.ceiba.ceibaestacionamiento.servicios.excepciones.SinAutorizacionException;
import co.com.ceiba.ceibaestacionamiento.servicios.excepciones.SinEspacioException;

public class Estacionamiento 
{
	private FacturaServicio facturaServicio;
	private MovilServicio movilServicio;
	private CostoEstadiaServicio costoEstadiaServicio;
	
	public static final int CANTIDAD_MAXIMA_CARROS = 20;
	public static final int CANTIDAD_MAXIMA_MOTOS = 10;
	public static final int MOVIL_NO_REGISTRADO = 0;
	public static final String SIN_ESPACIO_ESTACIONAMIENTO = "El estacionamiento no cuenta con espacio disponible.";
	public static final String SIN_AUTORIZACION_INGRESO = "No esta autorizado a ingresar.";
	public static final String MOVIL_REGISTRADO = "Este movil ya se encuentra en el estacionamiento.";
	public static final String MOVIL_NO_ENCONTRADO = "El movil no fue encontrado.";
	public static final String MOVIL_NO_ENCONTRADO_ESTACIONAMIENTO = "El movil no fue encontrado en el estacionamiento";
	private static final int TOTAL_HORAS_DIA = 24;
	private static final int MILISEGUNDOS_POR_SEGUNDO = 1000;
	private static final int SEGUNDOS_POR_HORA = 3600;
	private static final int MAX_HORAS_COBRO_HORA = 9;
	private static final int CILINDRAJE_VALOR_EXTENDIDO = 500;
	private static final String CARRO = "CARRO";
	private static final String MOTO = "MOTO";
	
	
	public Estacionamiento(FacturaServicio facturaServicio, MovilServicio movilServicio, CostoEstadiaServicio costoEstadiaServicio)
	{
		this.facturaServicio = facturaServicio;
		this.movilServicio = movilServicio;
		this.costoEstadiaServicio = costoEstadiaServicio;
	}
	
	public void hayEspacioEstacionamiento(Movil movil)
	{
		if (movil.getTipoMovil().equals(CARRO)) 
		{
			Integer cantCarrosEstacionamiento = this.facturaServicio.getCantidadMovilesByTipo(CARRO);
			if (cantCarrosEstacionamiento >= CANTIDAD_MAXIMA_CARROS) 
			{
				throw new SinEspacioException(SIN_ESPACIO_ESTACIONAMIENTO);
			}
		} 
		else 
		{
			Integer cantMotosEstacionamiento = this.facturaServicio.getCantidadMovilesByTipo(MOTO);
			if (cantMotosEstacionamiento >= CANTIDAD_MAXIMA_MOTOS) 
			{
				throw new SinEspacioException(SIN_ESPACIO_ESTACIONAMIENTO);
			}

		}
	}
	
	public boolean esDiaPermitido() 
	{
		Date now = new Date();
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
        String day = simpleDateformat.format(now);
        return day == "Sunday" || day == "Monday";
	}
	
	public void ingresaEnDiaPermitido(Movil movil) 
	{
		String placa = movil.getPlaca().toUpperCase();
		
		if(placa.startsWith("A") && !esDiaPermitido())
		{
			throw new SinAutorizacionException(SIN_AUTORIZACION_INGRESO);
		}
	}

	public void estaEnEstacionamiento(Movil movil)
	{
		String placa = movil.getPlaca();
		
		if(this.facturaServicio.getMovilEstacionamientoByPlaca(placa) != MOVIL_NO_REGISTRADO)
		{
			throw new MovilRegistradoException(MOVIL_REGISTRADO);
		}
	}
	
	public void crearMovilBase(Movil movil)
	{
		String placa = movil.getPlaca();
		
		if (this.movilServicio.getMovilByPlaca(placa) == null)
				this.movilServicio.registrarMovil(movil);
	}
	
	public boolean registrarMovil(Movil movil)
	{
		hayEspacioEstacionamiento(movil);
		ingresaEnDiaPermitido(movil);
		estaEnEstacionamiento(movil);
		crearMovilBase(movil);
		return this.facturaServicio.crearFactura(movil);
	}
	
	public void existeMovil(String placa)
	{
		if(movilServicio.getMovilByPlaca(placa) == null)
		{
			throw new MovilNoRegistradoException(MOVIL_NO_ENCONTRADO);
		}
	}
	
	public void existeMovilEstacionamiento(String placa)
	{
		if(facturaServicio.getMovilEstacionamientoByPlaca(placa) == MOVIL_NO_REGISTRADO)
		{
			throw new MovilNoRegistradoException(MOVIL_NO_ENCONTRADO_ESTACIONAMIENTO);
		}
	}
	
	public void validarPlaca(String placa)
	{
		existeMovil(placa);
		existeMovilEstacionamiento(placa);
	}
	
	public int getHorasEntreFechas(Factura factura)
	{
		Date fechaIngreso = factura.getFechaIngreso();
		Date fechaSalida = factura.getFechaSalida();
		
		long secs = (fechaSalida.getTime() - fechaIngreso.getTime()) / MILISEGUNDOS_POR_SEGUNDO;
		int horas = (int) (secs/SEGUNDOS_POR_HORA);
		int segRestantes = (int) (secs%SEGUNDOS_POR_HORA);
		
		if(segRestantes > 0){
			++horas;
		}
		return horas;
	}
	
	public List<Integer> obtenerCantDiasHoras(int horasLapso)
	{
		List<Integer> valores = new ArrayList<>();
		
		int diasLapso = horasLapso / TOTAL_HORAS_DIA;
		
		if(diasLapso < 1 && horasLapso < MAX_HORAS_COBRO_HORA )
		{
			valores.add(0, 0);
			valores.add(1, horasLapso);
		}
		else if(diasLapso < 1 && horasLapso > MAX_HORAS_COBRO_HORA)
		{
			valores.add(0, 1);
			valores.add(1, 0);
		}
		else if(diasLapso >= 1)
		{
			valores.add(0, diasLapso);
			
			int horasRestantes = horasLapso % TOTAL_HORAS_DIA;
			if(horasRestantes < MAX_HORAS_COBRO_HORA)
				valores.add(1, horasRestantes);
			else
			{
				valores.add(0, valores.get(0) + 1);
				valores.add(1, 0);
			}
		}
		return valores;
	}
	
	public double calcularValorFactura(String tipoMovil, int horasCobrar, int diasCobrar, double cilindraje)
	{
		double valorDia = costoEstadiaServicio.getCostoEstadiaBy(tipoMovil, "NORMAL", "DIA");
		double valorHora = costoEstadiaServicio.getCostoEstadiaBy(tipoMovil, "NORMAL", "HORA");
		return (int) (diasCobrar*valorDia + horasCobrar*valorHora + calcularValorExtendido(tipoMovil, cilindraje));
	}
	
	public double calcularValorExtendido(String tipoMovil, double cilindraje)
	{
		if(tipoMovil.equals(MOTO) && cilindraje > CILINDRAJE_VALOR_EXTENDIDO)
			return costoEstadiaServicio.getCostoEstadiaBy(tipoMovil, "EXTENDIDO", "NO_APLICA");
		return 0;
	}
	
	public double obtenerValorFactura(Factura factura)
	{
		int horasCobrar;
		int diasCobrar;
		int horasLapso;
		double valorCobrar = 0;
		
		horasLapso = getHorasEntreFechas(factura);
		List<Integer> valores = obtenerCantDiasHoras(horasLapso);
		
		diasCobrar = valores.get(0);
		horasCobrar = valores.get(1);

		String tipoMovil = factura.getMovil().getTipoMovil();
		double cilindraje =  factura.getMovil().getCilindraje();
		
		if(tipoMovil.equals(CARRO))
		{
			valorCobrar = calcularValorFactura(CARRO, horasCobrar, diasCobrar, cilindraje);
		}
		else if(tipoMovil.equals(MOTO))
		{
			valorCobrar = calcularValorFactura(MOTO, horasCobrar, diasCobrar, cilindraje);
		}
		
		return valorCobrar;
	}
	
	public Factura eliminarMovil(String placa)
	{
		validarPlaca(placa);
	
		Factura factura = facturaServicio.getFacturaByPlaca(placa);
		Date fechaSalida = new Date();
		factura.setFechaSalida(fechaSalida);
		
		factura.setValor(obtenerValorFactura(factura));
		
		factura = facturaServicio.actualizarFactura(factura);
		return factura;
	}
}
