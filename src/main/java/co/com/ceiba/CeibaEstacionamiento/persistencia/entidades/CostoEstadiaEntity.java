package co.com.ceiba.CeibaEstacionamiento.persistencia.entidades;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "costoestadia")
@Table(name = "costoestadia")
public class CostoEstadiaEntity
{	
	@Id
	@Column(length = 10)
	private int cos_id;
	
	@Column(name="cos_tipoMovil",length=5)
	private String cos_tipoMovil;
	
	@Column(name="cos_tipoPago",length=10)
	private String cos_tipoPago;
	
	@Column(name="cos_tiempoestadia",length=5)
	private String cos_tiempoestadia;
	
	@Column(name = "cos_valor", precision = 10, scale = 2)
	private double cos_valor;

	public CostoEstadiaEntity(int cos_id, String cos_tipoMovil, String cos_tipoPago, String cos_tiempoestadia,
			double cos_valor) {
		super();
		this.cos_id = cos_id;
		this.cos_tipoMovil = cos_tipoMovil;
		this.cos_tipoPago = cos_tipoPago;
		this.cos_tiempoestadia = cos_tiempoestadia;
		this.cos_valor = cos_valor;
	}
	
	public int getCos_id() {
		return cos_id;
	}

	public void setCos_id(int cos_id) {
		this.cos_id = cos_id;
	}

	public String getCos_tipoMovil() {
		return cos_tipoMovil;
	}

	public void setCos_tipoMovil(String cos_tipoMovil) {
		this.cos_tipoMovil = cos_tipoMovil;
	}

	public String getCos_tipoPago() {
		return cos_tipoPago;
	}

	public void setCos_tipoPago(String cos_tipoPago) {
		this.cos_tipoPago = cos_tipoPago;
	}

	public String getCos_tiempoestadia() {
		return cos_tiempoestadia;
	}

	public void setCos_tiempoestadia(String cos_tiempoestadia) {
		this.cos_tiempoestadia = cos_tiempoestadia;
	}

	public double getCos_valor() {
		return cos_valor;
	}

	public void setCos_valor(double cos_valor) {
		this.cos_valor = cos_valor;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CostoEstadiaEntity)) return false;
        CostoEstadiaEntity that = (CostoEstadiaEntity) o;
        return Objects.equals(getCos_tipoMovil(), that.getCos_tipoMovil()) &&
                Objects.equals(getCos_tipoPago(), that.getCos_tipoPago())&&
                Objects.equals(getCos_valor(), that.getCos_valor())&&
                Objects.equals(getCos_tiempoestadia(), that.getCos_tiempoestadia());
    }
	
    @Override
    public int hashCode() {
        return Objects.hash(getCos_tipoMovil(), getCos_tipoPago(), getCos_tiempoestadia(), getCos_valor());
    }
}

