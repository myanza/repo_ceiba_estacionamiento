package co.com.ceiba.ceibaestacionamiento.persistencia.entidades;

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
	@Column(name="cos_id", length = 10)
	private int cosId;
	
	@Column(name="cos_tipo_movil",length=5)
	private String cosTipoMovil;
	
	@Column(name="cos_tipo_pago",length=10)
	private String cosTipoPago;
	
	@Column(name="cos_tiempo_estadia",length=10)
	private String cosTiempoEstadia;
	
	@Column(name = "cos_valor", precision = 10, scale = 2)
	private double cosValor;

	public CostoEstadiaEntity(int cosId, String cosTipoMovil, String cosTipoPago, String cosTiempoEstadia,
			double cosValor) {
		super();
		this.cosId = cosId;
		this.cosTipoMovil = cosTipoMovil;
		this.cosTipoPago = cosTipoPago;
		this.cosTiempoEstadia = cosTiempoEstadia;
		this.cosValor = cosValor;
	}
	
	public int getCosId() {
		return cosId;
	}

	public void setCosId(int cosId) {
		this.cosId = cosId;
	}

	public String getCosTipoMovil() {
		return cosTipoMovil;
	}

	public void setCosTipoMovil(String cosTipoMovil) {
		this.cosTipoMovil = cosTipoMovil;
	}

	public String getCosTipoPago() {
		return cosTipoPago;
	}

	public void setCosTipoPago(String cosTipoPago) {
		this.cosTipoPago = cosTipoPago;
	}

	public String getCosTiempoestadia() {
		return cosTiempoEstadia;
	}

	public void setCosTiempoEstadia(String cosTiempoEstadia) {
		this.cosTiempoEstadia = cosTiempoEstadia;
	}

	public double getCosValor() {
		return cosValor;
	}

	public void setCosValor(double cosValor) {
		this.cosValor = cosValor;
	}

	@Override
    public boolean equals(Object o) 
	{
        if (this == o) 
        	return true;
        if (!(o instanceof CostoEstadiaEntity)) 
        	return false;
        CostoEstadiaEntity that = (CostoEstadiaEntity) o;
        return Objects.equals(getCosTipoMovil(), that.getCosTipoMovil()) &&
                Objects.equals(getCosTipoPago(), that.getCosTipoPago())&&
                Objects.equals(getCosValor(), that.getCosValor())&&
                Objects.equals(getCosTiempoestadia(), that.getCosTiempoestadia());
    }
	
    @Override
    public int hashCode() {
        return Objects.hash(getCosTipoMovil(), getCosTipoPago(), getCosTiempoestadia(), getCosValor());
    }
}

