/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import InstallationKIPiADetailComponent from '@/entities/installation-ki-pi-a/installation-ki-pi-a-details.vue';
import InstallationKIPiAClass from '@/entities/installation-ki-pi-a/installation-ki-pi-a-details.component';
import InstallationKIPiAService from '@/entities/installation-ki-pi-a/installation-ki-pi-a.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('InstallationKIPiA Management Detail Component', () => {
    let wrapper: Wrapper<InstallationKIPiAClass>;
    let comp: InstallationKIPiAClass;
    let installationKIPiAServiceStub: SinonStubbedInstance<InstallationKIPiAService>;

    beforeEach(() => {
      installationKIPiAServiceStub = sinon.createStubInstance<InstallationKIPiAService>(InstallationKIPiAService);

      wrapper = shallowMount<InstallationKIPiAClass>(InstallationKIPiADetailComponent, {
        store,
        i18n,
        localVue,
        provide: { installationKIPiAService: () => installationKIPiAServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundInstallationKIPiA = { id: 123 };
        installationKIPiAServiceStub.find.resolves(foundInstallationKIPiA);

        // WHEN
        comp.retrieveInstallationKIPiA(123);
        await comp.$nextTick();

        // THEN
        expect(comp.installationKIPiA).toBe(foundInstallationKIPiA);
      });
    });
  });
});
