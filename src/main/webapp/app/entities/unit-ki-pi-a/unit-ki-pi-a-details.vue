<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="unitKIPiA">
                <h2 class="jh-entity-heading"><span v-text="$t('kiptenderApp.unitKIPiA.detail.title')">UnitKIPiA</span> {{unitKIPiA.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('kiptenderApp.unitKIPiA.title')">Title</span>
                    </dt>
                    <dd>
                        <span>{{unitKIPiA.title}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('kiptenderApp.unitKIPiA.description')">Description</span>
                    </dt>
                    <dd>
                        <span>{{unitKIPiA.description}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('kiptenderApp.unitKIPiA.systemRSUPAZ')">System RSUPAZ</span>
                    </dt>
                    <dd>
                        <span>{{unitKIPiA.systemRSUPAZ}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('kiptenderApp.unitKIPiA.rootPlanPresence')">Root Plan Presence</span>
                    </dt>
                    <dd>
                        <span>{{unitKIPiA.rootPlanPresence}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('kiptenderApp.unitKIPiA.needProjectOrder')">Need Project Order</span>
                    </dt>
                    <dd>
                        <span>{{unitKIPiA.needProjectOrder}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('kiptenderApp.unitKIPiA.needProjectCalculation')">Need Project Calculation</span>
                    </dt>
                    <dd>
                        <span>{{unitKIPiA.needProjectCalculation}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('kiptenderApp.unitKIPiA.needElectricalHeating')">Need Electrical Heating</span>
                    </dt>
                    <dd>
                        <span>{{unitKIPiA.needElectricalHeating}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('kiptenderApp.unitKIPiA.numberAI')">Number AI</span>
                    </dt>
                    <dd>
                        <span>{{unitKIPiA.numberAI}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('kiptenderApp.unitKIPiA.numberAO')">Number AO</span>
                    </dt>
                    <dd>
                        <span>{{unitKIPiA.numberAO}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('kiptenderApp.unitKIPiA.numberDI')">Number DI</span>
                    </dt>
                    <dd>
                        <span>{{unitKIPiA.numberDI}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('kiptenderApp.unitKIPiA.numberDO')">Number DO</span>
                    </dt>
                    <dd>
                        <span>{{unitKIPiA.numberDO}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('kiptenderApp.unitKIPiA.basicLaborInput')">Basic Labor Input</span>
                    </dt>
                    <dd>
                        <span>{{unitKIPiA.basicLaborInput}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('kiptenderApp.unitKIPiA.kipWiringDiagramType')">Kip Wiring Diagram Type</span>
                    </dt>
                    <dd>
                        <div v-if="unitKIPiA.kipWiringDiagramType">
                            <router-link :to="{name: 'KIPWiringDiagramTypeView', params: {kIPWiringDiagramTypeId: unitKIPiA.kipWiringDiagramType.id}}">{{unitKIPiA.kipWiringDiagramType.id}}</router-link>
                        </div>
                    </dd>
                    <dt>
                        <span v-text="$t('kiptenderApp.unitKIPiA.meashurmentParameter')">Meashurment Parameter</span>
                    </dt>
                    <dd>
                        <div v-if="unitKIPiA.meashurmentParameter">
                            <router-link :to="{name: 'MeasurementParameterView', params: {measurementParameterId: unitKIPiA.meashurmentParameter.id}}">{{unitKIPiA.meashurmentParameter.id}}</router-link>
                        </div>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="unitKIPiA.id" :to="{name: 'UnitKIPiAEdit', params: {unitKIPiAId: unitKIPiA.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./unit-ki-pi-a-details.component.ts">
</script>
