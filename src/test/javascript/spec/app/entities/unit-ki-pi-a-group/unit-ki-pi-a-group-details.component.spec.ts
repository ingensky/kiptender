/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import UnitKIPiAGroupDetailComponent from '@/entities/unit-ki-pi-a-group/unit-ki-pi-a-group-details.vue';
import UnitKIPiAGroupClass from '@/entities/unit-ki-pi-a-group/unit-ki-pi-a-group-details.component';
import UnitKIPiAGroupService from '@/entities/unit-ki-pi-a-group/unit-ki-pi-a-group.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('UnitKIPiAGroup Management Detail Component', () => {
    let wrapper: Wrapper<UnitKIPiAGroupClass>;
    let comp: UnitKIPiAGroupClass;
    let unitKIPiAGroupServiceStub: SinonStubbedInstance<UnitKIPiAGroupService>;

    beforeEach(() => {
      unitKIPiAGroupServiceStub = sinon.createStubInstance<UnitKIPiAGroupService>(UnitKIPiAGroupService);

      wrapper = shallowMount<UnitKIPiAGroupClass>(UnitKIPiAGroupDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { unitKIPiAGroupService: () => unitKIPiAGroupServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundUnitKIPiAGroup = { id: 123 };
        unitKIPiAGroupServiceStub.find.resolves(foundUnitKIPiAGroup);

        // WHEN
        comp.retrieveUnitKIPiAGroup(123);
        await comp.$nextTick();

        // THEN
        expect(comp.unitKIPiAGroup).toBe(foundUnitKIPiAGroup);
      });
    });
  });
});
