<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="tender">
                <h2 class="jh-entity-heading"><span v-text="$t('kiptenderApp.tender.detail.title')">Tender</span> {{tender.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('kiptenderApp.tender.number')">Number</span>
                    </dt>
                    <dd>
                        <span>{{tender.number}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('kiptenderApp.tender.title')">Title</span>
                    </dt>
                    <dd>
                        <span>{{tender.title}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('kiptenderApp.tender.description')">Description</span>
                    </dt>
                    <dd>
                        <span>{{tender.description}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('kiptenderApp.tender.location')">Location</span>
                    </dt>
                    <dd>
                        <span>{{tender.location}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('kiptenderApp.tender.worktrip')">Worktrip</span>
                    </dt>
                    <dd>
                        <span>{{tender.worktrip}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('kiptenderApp.tender.needSIDStage')">Need SID Stage</span>
                    </dt>
                    <dd>
                        <span>{{tender.needSIDStage}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('kiptenderApp.tender.needOTRStage')">Need OTR Stage</span>
                    </dt>
                    <dd>
                        <span>{{tender.needOTRStage}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('kiptenderApp.tender.projectMark')">Project Mark</span>
                    </dt>
                    <dd>
                        <span v-for="(projectMark, i) in tender.projectMarks" :key="projectMark.id">{{i > 0 ? ', ' : ''}}
                            <router-link :to="{name: 'ProjectMarkView', params: {projectMarkId: projectMark.id}}">{{projectMark.id}}</router-link>
                        </span>
                    </dd>
                    <dt>
                        <span v-text="$t('kiptenderApp.tender.unitKIPiAGroup')">Unit KI Pi A Group</span>
                    </dt>
                    <dd>
                        <span v-for="(unitKIPiAGroup, i) in tender.unitKIPiAGroups" :key="unitKIPiAGroup.id">{{i > 0 ? ', ' : ''}}
                            <router-link :to="{name: 'UnitKIPiAGroupView', params: {unitKIPiAGroupId: unitKIPiAGroup.id}}">{{unitKIPiAGroup.id}}</router-link>
                        </span>
                    </dd>
                    <dt>
                        <span v-text="$t('kiptenderApp.tender.installationKIPiAGroup')">Installation KI Pi A Group</span>
                    </dt>
                    <dd>
                        <span v-for="(installationKIPiAGroup, i) in tender.installationKIPiAGroups" :key="installationKIPiAGroup.id">{{i > 0 ? ', ' : ''}}
                            <router-link :to="{name: 'InstallationKIPiAGroupView', params: {installationKIPiAGroupId: installationKIPiAGroup.id}}">{{installationKIPiAGroup.id}}</router-link>
                        </span>
                    </dd>
                    <dt>
                        <span v-text="$t('kiptenderApp.tender.client')">Client</span>
                    </dt>
                    <dd>
                        <div v-if="tender.client">
                            <router-link :to="{name: 'ClientView', params: {clientId: tender.client.id}}">{{tender.client.id}}</router-link>
                        </div>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="tender.id" :to="{name: 'TenderEdit', params: {tenderId: tender.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./tender-details.component.ts">
</script>
