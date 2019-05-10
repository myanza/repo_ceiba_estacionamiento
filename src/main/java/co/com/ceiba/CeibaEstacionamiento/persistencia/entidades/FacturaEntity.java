package co.com.ceiba.CeibaEstacionamiento.persistencia.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity(name="factura")
@Table(name="factura")
public class FacturaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int fac_id;

	@Column(name = "fac_fechaIngreso")
	private Date fac_fechaIngreso;

	@Column(name = "fac_fechaSalida", nullable = true)
	private Date fac_fechaSalida;

	@Column(name = "fac_valor", nullable = true, precision = 10, scale = 2)
	private double fac_valor;

	@ManyToOne
	@JoinColumn(name = "mov_placa")
	private MovilEntity movil;

	public int getFacId() {
		return fac_id;
	}

	public void setFacId(int fac_id) {
		this.fac_id = fac_id;
	}

	public Date getFacFechaIngreso() {
		return fac_fechaIngreso;
	}

	public void setFacFechaIngreso(Date fac_fechaIngreso) {
		this.fac_fechaIngreso = fac_fechaIngreso;
	}

	public Date getFacFechaSalida() {
		return fac_fechaSalida;
	}

	public void setFacFechaSalida(Date fac_fechaSalida) {
		this.fac_fechaSalida = fac_fechaSalida;
	}

	public double getFacValor() {
		return fac_valor;
	}

	public void setFacValor(double fac_valor) {
		this.fac_valor = fac_valor;
	}

	public MovilEntity getMovil() {
		return movil;
	}

	public void setMovil(MovilEntity movil) {
		this.movil = movil;
	}

	
}
