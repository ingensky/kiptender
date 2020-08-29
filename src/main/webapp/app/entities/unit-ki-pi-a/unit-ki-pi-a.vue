<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('kiptenderApp.unitKIPiA.home.title')" id="unit-ki-pi-a-heading">Unit KI Pi AS</span>
            <router-link :to="{name: 'UnitKIPiACreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-unit-ki-pi-a">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('kiptenderApp.unitKIPiA.home.createLabel')">
                    Create a new Unit KI Pi A
                </span>
            </router-link>
        </h2>
        <b-alert :show="dismissCountDown"
            dismissible
            :variant="alertType"
            @dismissed="dismissCountDown=0"
            @dismiss-count-down="countDownChanged">
            {{alertMessage}}
        </b-alert>
        <br/>
        <div class="alert alert-warning" v-if="!isFetching && unitKIPiAS && unitKIPiAS.length === 0">
            <span v-text="$t('kiptenderApp.unitKIPiA.home.notFound')">No unitKIPiAS found</span>
        </div>
        <div class="table-responsive" v-if="unitKIPiAS && unitKIPiAS.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('title')"><span v-text="$t('kiptenderApp.unitKIPiA.title')">Title</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'title'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('description')"><span v-text="$t('kiptenderApp.unitKIPiA.description')">Description</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'description'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('systemRSUPAZ')"><span v-text="$t('kiptenderApp.unitKIPiA.systemRSUPAZ')">System RSUPAZ</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'systemRSUPAZ'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('rootPlanPresence')"><span v-text="$t('kiptenderApp.unitKIPiA.rootPlanPresence')">Root Plan Presence</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'rootPlanPresence'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('needProjectOrder')"><span v-text="$t('kiptenderApp.unitKIPiA.needProjectOrder')">Need Project Order</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'needProjectOrder'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('needProjectCalculation')"><span v-text="$t('kiptenderApp.unitKIPiA.needProjectCalculation')">Need Project Calculation</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'needProjectCalculation'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('needElectricalHeating')"><span v-text="$t('kiptenderApp.unitKIPiA.needElectricalHeating')">Need Electrical Heating</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'needElectricalHeating'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('numberAI')"><span v-text="$t('kiptenderApp.unitKIPiA.numberAI')">Number AI</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'numberAI'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('numberAO')"><span v-text="$t('kiptenderApp.unitKIPiA.numberAO')">Number AO</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'numberAO'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('numberDI')"><span v-text="$t('kiptenderApp.unitKIPiA.numberDI')">Number DI</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'numberDI'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('numberDO')"><span v-text="$t('kiptenderApp.unitKIPiA.numberDO')">Number DO</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'numberDO'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('basicLaborInput')"><span v-text="$t('kiptenderApp.unitKIPiA.basicLaborInput')">Basic Labor Input</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'basicLaborInput'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('kipWiringDiagramType.id')"><span v-text="$t('kiptenderApp.unitKIPiA.kipWiringDiagramType')">Kip Wiring Diagram Type</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'kipWiringDiagramType.id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('meashurmentParameter.id')"><span v-text="$t('kiptenderApp.unitKIPiA.meashurmentParameter')">Meashurment Parameter</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'meashurmentParameter.id'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="unitKIPiA in unitKIPiAS"
                    :key="unitKIPiA.id">
                    <td>
                        <router-link :to="{name: 'UnitKIPiAView', params: {unitKIPiAId: unitKIPiA.id}}">{{unitKIPiA.id}}</router-link>
                    </td>
                    <td>{{unitKIPiA.title}}</td>
                    <td>{{unitKIPiA.description}}</td>
                    <td>{{unitKIPiA.systemRSUPAZ}}</td>
                    <td>{{unitKIPiA.rootPlanPresence}}</td>
                    <td>{{unitKIPiA.needProjectOrder}}</td>
                    <td>{{unitKIPiA.needProjectCalculation}}</td>
                    <td>{{unitKIPiA.needElectricalHeating}}</td>
                    <td>{{unitKIPiA.numberAI}}</td>
                    <td>{{unitKIPiA.numberAO}}</td>
                    <td>{{unitKIPiA.numberDI}}</td>
                    <td>{{unitKIPiA.numberDO}}</td>
                    <td>{{unitKIPiA.basicLaborInput}}</td>
                    <td>
                        <div v-if="unitKIPiA.kipWiringDiagramType">
                            <router-link :to="{name: 'KIPWiringDiagramTypeView', params: {kIPWiringDiagramTypeId: unitKIPiA.kipWiringDiagramType.id}}">{{unitKIPiA.kipWiringDiagramType.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="unitKIPiA.meashurmentParameter">
                            <router-link :to="{name: 'MeasurementParameterView', params: {measurementParameterId: unitKIPiA.meashurmentParameter.id}}">{{unitKIPiA.meashurmentParameter.id}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'UnitKIPiAView', params: {unitKIPiAId: unitKIPiA.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'UnitKIPiAEdit', params: {unitKIPiAId: unitKIPiA.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(unitKIPiA)"
                                   variant="danger"
                                   class="btn btn-sm"
                                   v-b-modal.removeEntity>
                                <font-awesome-icon icon="times"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                            </b-button>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <b-modal ref="removeEntity" id="removeEntity" >
            <span slot="modal-title"><span id="kiptenderApp.unitKIPiA.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-unitKIPiA-heading" v-text="$t('kiptenderApp.unitKIPiA.delete.question', {'id': removeId})">Are you sure you want to delete this Unit KI Pi A?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-unitKIPiA" v-text="$t('entity.action.delete')" v-on:click="removeUnitKIPiA()">Delete</button>
            </div>
        </b-modal>
        <div v-show="unitKIPiAS && unitKIPiAS.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./unit-ki-pi-a.component.ts">
</script>
