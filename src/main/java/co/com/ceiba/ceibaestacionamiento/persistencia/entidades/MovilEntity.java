
package co.com.ceiba.ceibaestacionamiento.persistencia.entidades;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity(name = "movil")
@Table(name = "movil")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@mov_placa")
public class MovilEntity {

	@Id
	@Column(name="mov_placa", length = 10)
	private String movPlaca;

	@Column(name = "mov_cilindraje", nullable = true, precision = 10, scale = 2)
	private double movCilindraje;

	@Column(name = "mov_tipo_movil", nullable = true, length = 5)
	private String movTipoMovil;

	@OneToMany(mappedBy = "movil", cascade = CascadeType.ALL)
	//@JsonManagedReference
	private Set<FacturaEntity> facturasEntity;

	public String getMovPlaca() {
		return movPlaca;
	}

	public void setMovPlaca(String placa) {
		this.movPlaca = placa;
	}

	public double getMovCilindraje() {
		return movCilindraje;
	}

	public void setMovCilindraje(double cilindraje) {
		this.movCilindraje = cilindraje;
	}

	public String getMovTipoMovil() {
		return movTipoMovil;
	}

	public void setMovTipoMovil(String tipoVehiculo) {
		this.movTipoMovil = tipoVehiculo;
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
