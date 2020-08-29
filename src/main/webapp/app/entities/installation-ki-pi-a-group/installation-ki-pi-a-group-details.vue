<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="installationKIPiAGroup">
                <h2 class="jh-entity-heading"><span v-text="$t('kiptenderApp.installationKIPiAGroup.detail.title')">InstallationKIPiAGroup</span> {{installationKIPiAGroup.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('kiptenderApp.installationKIPiAGroup.quantity')">Quantity</span>
                    </dt>
                    <dd>
                        <span>{{installationKIPiAGroup.quantity}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('kiptenderApp.installationKIPiAGroup.installationKIPiA')">Installation KI Pi A</span>
                    </dt>
                    <dd>
                        <span v-for="(installationKIPiA, i) in installationKIPiAGroup.installationKIPiAS" :key="installationKIPiA.id">{{i > 0 ? ', ' : ''}}
                            <router-link :to="{name: 'InstallationKIPiAView', params: {installationKIPiAId: installationKIPiA.id}}">{{installationKIPiA.id}}</router-link>
                        </span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="installationKIPiAGroup.id" :to="{name: 'InstallationKIPiAGroupEdit', params: {installationKIPiAGroupId: installationKIPiAGroup.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./installation-ki-pi-a-group-details.component.ts">
</script>
