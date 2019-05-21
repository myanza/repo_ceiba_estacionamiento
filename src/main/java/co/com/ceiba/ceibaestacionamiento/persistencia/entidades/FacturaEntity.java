package co.com.ceiba.ceibaestacionamiento.persistencia.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity(name="factura")
@Table(name="factura")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@fac_id")
public class FacturaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int facId;

	@Column(name = "fac_fecha_ingreso")
	private Date facFechaIngreso;

	@Column(name = "fac_fecha_salida", nullable = true)
	private Date facFechaSalida;

	@Column(name = "fac_valor", nullable = true, precision = 10, scale = 2)
	private double facValor;

	@ManyToOne
	@JoinColumn(name = "mov_placa")
	//@JsonBackReference
	private MovilEntity movil;

	public int getFacId() {
		return facId;
	}

	public void setFacId(int facId) {
		this.facId = facId;
	}

	public Date getFacFechaIngreso() {
		return facFechaIngreso;
	}

	public void setFacFechaIngreso(Date facFechaIngreso) {
		this.facFechaIngreso = facFechaIngreso;
	}

	public Date getFacFechaSalida() {
		return facFechaSalida;
	}

	public void setFacFechaSalida(Date facFechaSalida) {
		this.facFechaSalida = facFechaSalida;
	}

	public double getFacValor() {
		return facValor;
	}

	public void setFacValor(double facValor) {
		this.facValor = facValor;
	}

	public MovilEntity getMovil() {
		return movil;
	}

	public void setMovil(MovilEntity movil) {
		this.movil = movil;
	}

	
}
