/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import InstallationKIPiAGroupDetailComponent from '@/entities/installation-ki-pi-a-group/installation-ki-pi-a-group-details.vue';
import InstallationKIPiAGroupClass from '@/entities/installation-ki-pi-a-group/installation-ki-pi-a-group-details.component';
import InstallationKIPiAGroupService from '@/entities/installation-ki-pi-a-group/installation-ki-pi-a-group.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('InstallationKIPiAGroup Management Detail Component', () => {
    let wrapper: Wrapper<InstallationKIPiAGroupClass>;
    let comp: InstallationKIPiAGroupClass;
    let installationKIPiAGroupServiceStub: SinonStubbedInstance<InstallationKIPiAGroupService>;

    beforeEach(() => {
      installationKIPiAGroupServiceStub = sinon.createStubInstance<InstallationKIPiAGroupService>(InstallationKIPiAGroupService);

      wrapper = shallowMount<InstallationKIPiAGroupClass>(InstallationKIPiAGroupDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { installationKIPiAGroupService: () => installationKIPiAGroupServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundInstallationKIPiAGroup = { id: 123 };
        installationKIPiAGroupServiceStub.find.resolves(foundInstallationKIPiAGroup);

        // WHEN
        comp.retrieveInstallationKIPiAGroup(123);
        await comp.$nextTick();

        // THEN
        expect(comp.installationKIPiAGroup).toBe(foundInstallationKIPiAGroup);
      });
    });
  });
});
