<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="kiptenderApp.unitKIPiA.home.createOrEditLabel" v-text="$t('kiptenderApp.unitKIPiA.home.createOrEditLabel')">Create or edit a UnitKIPiA</h2>
                <div>
                    <div class="form-group" v-if="unitKIPiA.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="unitKIPiA.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kiptenderApp.unitKIPiA.title')" for="unit-ki-pi-a-title">Title</label>
                        <input type="text" class="form-control" name="title" id="unit-ki-pi-a-title"
                            :class="{'valid': !$v.unitKIPiA.title.$invalid, 'invalid': $v.unitKIPiA.title.$invalid }" v-model="$v.unitKIPiA.title.$model"  required/>
                        <div v-if="$v.unitKIPiA.title.$anyDirty && $v.unitKIPiA.title.$invalid">
                            <small class="form-text text-danger" v-if="!$v.unitKIPiA.title.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.unitKIPiA.title.minLength" v-text="$t('entity.validation.minlength', {min: 3})">
                                This field is required to be at least 3 characters.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kiptenderApp.unitKIPiA.description')" for="unit-ki-pi-a-description">Description</label>
                        <input type="text" class="form-control" name="description" id="unit-ki-pi-a-description"
                            :class="{'valid': !$v.unitKIPiA.description.$invalid, 'invalid': $v.unitKIPiA.description.$invalid }" v-model="$v.unitKIPiA.description.$model" />
                        <div v-if="$v.unitKIPiA.description.$anyDirty && $v.unitKIPiA.description.$invalid">
                            <small class="form-text text-danger" v-if="!$v.unitKIPiA.description.minLength" v-text="$t('entity.validation.minlength', {min: 3})">
                                This field is required to be at least 3 characters.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kiptenderApp.unitKIPiA.systemRSUPAZ')" for="unit-ki-pi-a-systemRSUPAZ">System RSUPAZ</label>
                        <input type="checkbox" class="form-check" name="systemRSUPAZ" id="unit-ki-pi-a-systemRSUPAZ"
                            :class="{'valid': !$v.unitKIPiA.systemRSUPAZ.$invalid, 'invalid': $v.unitKIPiA.systemRSUPAZ.$invalid }" v-model="$v.unitKIPiA.systemRSUPAZ.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kiptenderApp.unitKIPiA.rootPlanPresence')" for="unit-ki-pi-a-rootPlanPresence">Root Plan Presence</label>
                        <input type="checkbox" class="form-check" name="rootPlanPresence" id="unit-ki-pi-a-rootPlanPresence"
                            :class="{'valid': !$v.unitKIPiA.rootPlanPresence.$invalid, 'invalid': $v.unitKIPiA.rootPlanPresence.$invalid }" v-model="$v.unitKIPiA.rootPlanPresence.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kiptenderApp.unitKIPiA.needProjectOrder')" for="unit-ki-pi-a-needProjectOrder">Need Project Order</label>
                        <input type="checkbox" class="form-check" name="needProjectOrder" id="unit-ki-pi-a-needProjectOrder"
                            :class="{'valid': !$v.unitKIPiA.needProjectOrder.$invalid, 'invalid': $v.unitKIPiA.needProjectOrder.$invalid }" v-model="$v.unitKIPiA.needProjectOrder.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kiptenderApp.unitKIPiA.needProjectCalculation')" for="unit-ki-pi-a-needProjectCalculation">Need Project Calculation</label>
                        <input type="checkbox" class="form-check" name="needProjectCalculation" id="unit-ki-pi-a-needProjectCalculation"
                            :class="{'valid': !$v.unitKIPiA.needProjectCalculation.$invalid, 'invalid': $v.unitKIPiA.needProjectCalculation.$invalid }" v-model="$v.unitKIPiA.needProjectCalculation.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kiptenderApp.unitKIPiA.needElectricalHeating')" for="unit-ki-pi-a-needElectricalHeating">Need Electrical Heating</label>
                        <input type="checkbox" class="form-check" name="needElectricalHeating" id="unit-ki-pi-a-needElectricalHeating"
                            :class="{'valid': !$v.unitKIPiA.needElectricalHeating.$invalid, 'invalid': $v.unitKIPiA.needElectricalHeating.$invalid }" v-model="$v.unitKIPiA.needElectricalHeating.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kiptenderApp.unitKIPiA.numberAI')" for="unit-ki-pi-a-numberAI">Number AI</label>
                        <input type="number" class="form-control" name="numberAI" id="unit-ki-pi-a-numberAI"
                            :class="{'valid': !$v.unitKIPiA.numberAI.$invalid, 'invalid': $v.unitKIPiA.numberAI.$invalid }" v-model.number="$v.unitKIPiA.numberAI.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kiptenderApp.unitKIPiA.numberAO')" for="unit-ki-pi-a-numberAO">Number AO</label>
                        <input type="number" class="form-control" name="numberAO" id="unit-ki-pi-a-numberAO"
                            :class="{'valid': !$v.unitKIPiA.numberAO.$invalid, 'invalid': $v.unitKIPiA.numberAO.$invalid }" v-model.number="$v.unitKIPiA.numberAO.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kiptenderApp.unitKIPiA.numberDI')" for="unit-ki-pi-a-numberDI">Number DI</label>
                        <input type="number" class="form-control" name="numberDI" id="unit-ki-pi-a-numberDI"
                            :class="{'valid': !$v.unitKIPiA.numberDI.$invalid, 'invalid': $v.unitKIPiA.numberDI.$invalid }" v-model.number="$v.unitKIPiA.numberDI.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kiptenderApp.unitKIPiA.numberDO')" for="unit-ki-pi-a-numberDO">Number DO</label>
                        <input type="number" class="form-control" name="numberDO" id="unit-ki-pi-a-numberDO"
                            :class="{'valid': !$v.unitKIPiA.numberDO.$invalid, 'invalid': $v.unitKIPiA.numberDO.$invalid }" v-model.number="$v.unitKIPiA.numberDO.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kiptenderApp.unitKIPiA.basicLaborInput')" for="unit-ki-pi-a-basicLaborInput">Basic Labor Input</label>
                        <input type="number" class="form-control" name="basicLaborInput" id="unit-ki-pi-a-basicLaborInput"
                            :class="{'valid': !$v.unitKIPiA.basicLaborInput.$invalid, 'invalid': $v.unitKIPiA.basicLaborInput.$invalid }" v-model.number="$v.unitKIPiA.basicLaborInput.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kiptenderApp.unitKIPiA.kipWiringDiagramType')" for="unit-ki-pi-a-kipWiringDiagramType">Kip Wiring Diagram Type</label>
                        <select class="form-control" id="unit-ki-pi-a-kipWiringDiagramType" name="kipWiringDiagramType" v-model="unitKIPiA.kipWiringDiagramType">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="unitKIPiA.kipWiringDiagramType && kIPWiringDiagramTypeOption.id === unitKIPiA.kipWiringDiagramType.id ? unitKIPiA.kipWiringDiagramType : kIPWiringDiagramTypeOption" v-for="kIPWiringDiagramTypeOption in kIPWiringDiagramTypes" :key="kIPWiringDiagramTypeOption.id">{{kIPWiringDiagramTypeOption.id}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kiptenderApp.unitKIPiA.meashurmentParameter')" for="unit-ki-pi-a-meashurmentParameter">Meashurment Parameter</label>
                        <select class="form-control" id="unit-ki-pi-a-meashurmentParameter" name="meashurmentParameter" v-model="unitKIPiA.meashurmentParameter">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="unitKIPiA.meashurmentParameter && measurementParameterOption.id === unitKIPiA.meashurmentParameter.id ? unitKIPiA.meashurmentParameter : measurementParameterOption" v-for="measurementParameterOption in measurementParameters" :key="measurementParameterOption.id">{{measurementParameterOption.id}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.unitKIPiA.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./unit-ki-pi-a-update.component.ts">
</script>
