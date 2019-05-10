
package co.com.ceiba.CeibaEstacionamiento.persistencia.entidades;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "movil")
@Table(name = "movil")
public class MovilEntity {

	@Id
	@Column(length = 10)
	private String mov_placa;

	@Column(name = "mov_cilindraje", nullable = true, precision = 10, scale = 2)
	private double mov_cilindraje;

	@Column(name = "mov_tipoMovil", nullable = true, length = 5)
	private String mov_tipoMovil;

	@OneToMany(mappedBy = "movil", cascade = CascadeType.ALL)
	private Set<FacturaEntity> facturasEntity;

	public String getMovPlaca() {
		return mov_placa;
	}

	public void setMovPlaca(String placa) {
		this.mov_placa = placa;
	}

	public double getMovCilindraje() {
		return mov_cilindraje;
	}

	public void setMovCilindraje(double cilindraje) {
		this.mov_cilindraje = cilindraje;
	}

	public String getMovTipoMovil() {
		return mov_tipoMovil;
	}

	public void setMovTipoMovil(String tipoVehiculo) {
		this.mov_tipoMovil = tipoVehiculo;
	}

	public Set<FacturaEntity> getFacturasEntity() {
		return facturasEntity;
	}

	public void setFacturasEntity(Set<FacturaEntity> facturasEntity) {
		this.facturasEntity = facturasEntity;
	}

	public void agregarFactura(FacturaEntity facturaEntity) {
		if (this.facturasEntity == null)
			this.facturasEntity = new HashSet<>();
		this.facturasEntity.add(facturaEntity);
		facturaEntity.setMovil(this);
	}

}
