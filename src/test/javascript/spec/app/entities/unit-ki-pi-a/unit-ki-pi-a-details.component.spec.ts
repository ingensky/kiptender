/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import UnitKIPiADetailComponent from '@/entities/unit-ki-pi-a/unit-ki-pi-a-details.vue';
import UnitKIPiAClass from '@/entities/unit-ki-pi-a/unit-ki-pi-a-details.component';
import UnitKIPiAService from '@/entities/unit-ki-pi-a/unit-ki-pi-a.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('UnitKIPiA Management Detail Component', () => {
    let wrapper: Wrapper<UnitKIPiAClass>;
    let comp: UnitKIPiAClass;
    let unitKIPiAServiceStub: SinonStubbedInstance<UnitKIPiAService>;

    beforeEach(() => {
      unitKIPiAServiceStub = sinon.createStubInstance<UnitKIPiAService>(UnitKIPiAService);

      wrapper = shallowMount<UnitKIPiAClass>(UnitKIPiADetailComponent, {
        store,
        i18n,
        localVue,
        provide: { unitKIPiAService: () => unitKIPiAServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundUnitKIPiA = { id: 123 };
        unitKIPiAServiceStub.find.resolves(foundUnitKIPiA);

        // WHEN
        comp.retrieveUnitKIPiA(123);
        await comp.$nextTick();

        // THEN
        expect(comp.unitKIPiA).toBe(foundUnitKIPiA);
      });
    });
  });
});
