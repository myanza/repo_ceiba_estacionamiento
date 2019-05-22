import { AppPage } from './app.po';
import { browser, logging, element, by, promise } from 'protractor';
import { By } from 'selenium-webdriver';

describe('workspace-project App', () =>
{
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('Debe mostrar el mensaje de bienvenida', () =>
  {
    page.navigateTo();
    expect(page.getTitleText()).toEqual('Bienvenido al Estacionamiento Ceiba');
  });

  it('Debe registrar un movil', () =>
  {
    page.navigateTo();
    var tbxplaca = element(by.name('placaRegistro'));
    element(By.id('CARRO')).click();

    tbxplaca.sendKeys('FGH-123');

    element(by.name('btnRegistrar')).click();

    var tabla = element(by.tagName('mat-table'));
    var cells = tabla.all(by.tagName('mat-cell'));

    expect(cells.get(0).getText()).toEqual('FGH-123');
    expect(cells.get(1).getText()).toEqual('CARRO');
  });

  /*it('Debe eliminar el movil que se acaba de insertar', () =>
  {
    var placaMovil = 'FGH-123';
    page.navigateTo();

    var tabla = element(by.tagName('mat-table'));
    var filas = tabla.element(by.tagName('ng-container'));
    //var columnas = filas.element(By.css('.placaMovil'));

    filas.filter((fila): boolean|promise.Promise<boolean> =>
    {
      return fila.element(by.tagName('mat-cell')).getText().then((placa) =>
      {
        return placa === placaMovil;
      });
    })
    .get(0)
    .element(by.name('btnEliminar')).click();
  });*/

  afterEach(async () => {
    // Assert that there are no errors emitted from the browser
    const logs = await browser.manage().logs().get(logging.Type.BROWSER);
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.SEVERE,
    } as logging.Entry));
  });
});
