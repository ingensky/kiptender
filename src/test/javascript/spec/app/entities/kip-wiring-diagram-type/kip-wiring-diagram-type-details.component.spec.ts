/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import KIPWiringDiagramTypeDetailComponent from '@/entities/kip-wiring-diagram-type/kip-wiring-diagram-type-details.vue';
import KIPWiringDiagramTypeClass from '@/entities/kip-wiring-diagram-type/kip-wiring-diagram-type-details.component';
import KIPWiringDiagramTypeService from '@/entities/kip-wiring-diagram-type/kip-wiring-diagram-type.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('KIPWiringDiagramType Management Detail Component', () => {
    let wrapper: Wrapper<KIPWiringDiagramTypeClass>;
    let comp: KIPWiringDiagramTypeClass;
    let kIPWiringDiagramTypeServiceStub: SinonStubbedInstance<KIPWiringDiagramTypeService>;

    beforeEach(() => {
      kIPWiringDiagramTypeServiceStub = sinon.createStubInstance<KIPWiringDiagramTypeService>(KIPWiringDiagramTypeService);

      wrapper = shallowMount<KIPWiringDiagramTypeClass>(KIPWiringDiagramTypeDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { kIPWiringDiagramTypeService: () => kIPWiringDiagramTypeServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundKIPWiringDiagramType = { id: 123 };
        kIPWiringDiagramTypeServiceStub.find.resolves(foundKIPWiringDiagramType);

        // WHEN
        comp.retrieveKIPWiringDiagramType(123);
        await comp.$nextTick();

        // THEN
        expect(comp.kIPWiringDiagramType).toBe(foundKIPWiringDiagramType);
      });
    });
  });
});
