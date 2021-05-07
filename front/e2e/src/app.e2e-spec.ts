import { browser, logging } from 'protractor';
import { AppPage } from './app.po';
import {GlobalConstants} from '../../src/app/global-constants';
describe('workspace-project App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display welcome message', async () => {
    await page.navigateTo();
    expect(await page.getTitleText()).toEqual(GlobalConstants.siteTitle);
  });

  afterEach(async () => {
    // Assert that there are no errors emitted from the browser
    const logs = await browser.manage().logs().get(logging.Type.BROWSER);
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.SEVERE,
    } as logging.Entry));
  });
});
