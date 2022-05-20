<template>
    <div class="signIn">
        <div class="app">
            <div class="vertical-center">
                <div class="inner-block-2">
                    <div class="signIn">
                        <form @submit.prevent>
                            <h3>Report</h3>
                            
                            <div class="form-check form-check-inline">
                              <input :checked="hasShowedUp" v-model="showedUp" class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="showedUp">
                              <label class="form-check-label" for="inlineRadio1" click="penalizeClient"> Client showed up</label>
                            </div>
                            
                            <div class="form-check form-check-inline float-end">
                              <input v-model="showedUp" class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="didntShowUp">
                              <label class="form-check-label" for="inlineRadio2">Client didn't show up</label>
                            </div>
                            
                            <div class="form-group required" v-if="hasShowedUp">
                                <textarea style="min-width: 96%" placeholder="Enter your comment about client:" v-model="form.comment"></textarea>
                            </div>
                            <div class="form-group form-check-inline" v-if="hasShowedUp">
                                <button type="button" class="btn btn-primary" click="sendReport" :disabled="$v.form.$invalid"> Send report </button>
                            </div>
                            <div class="form-group form-check-inline float-end" v-if="hasShowedUp">
                                <button type="button" class="btn btn-danger" :disabled="$v.form.$invalid"> Ask for a penalization </button>
                            </div>

                            <div class="form-group" v-if="!hasShowedUp">
                                <button type="button" class="btn btn-danger" click="askForPenal"> Penalize client </button>
                            </div>
                            
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
    import Vue from 'vue'
    import Vuelidate from 'vuelidate'
    import { required, minLength } from 'vuelidate/lib/validators'
    import axios from 'axios'
    import VueAxios from 'vue-axios'
    import router from '@/router'

    Vue.use(VueAxios, axios)
    Vue.use(Vuelidate)

    export default({
        name: 'WriteAReport',
        components: {

        },
        data () {
            return {
                form: {
                    clientCame: true,
                    comment: ""
                },
                showedUp: "showedUp",
            }
        },
        computed: {
            hasShowedUp() {
                if(this.showedUp === "showedUp"){
                    return true;
                } else if (this.showedUp === "didntShowUp"){
                    return false;
                }
                return true;
            },
        },
        methods: {
            penalizeClient(){
                let path = this.getLoggedUser();
                axios.put("http://localhost:8088/" + path + "/penalizeClient",{
                    id: this.$route.params.id,
                    comment: "Client didn't show up."
                },
                {
                    headers: {
                        Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
                    }
                }).then(() => {
                    router.push("/reservation-history-privileged-user")
                })
            },
            sendReport(){
                let path = this.getLoggedUser();
                axios.put("http://localhost:8088/" + path + "/sendReport", {
                    id: this.$route.params.id,
                    comment: this.form.comment
                },
                {
                    headers: {
                        Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
                    }
                }).then(() => {
                    router.push("/reservation-history-privileged-user")
                })
            },
            askForPenal(){
                let path = this.getLoggedUser();
                axios.put("http://localhost:8088/" + path + "/askForPenal", {
                    id: this.$route.params.id,
                    comment: this.form.comment
                },
                {
                    headers: {
                        Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
                    }
                }).then(() => {
                    router.push("/reservation-history-privileged-user")
                })
            },
            getLoggedUser(){
                if (window.sessionStorage.getItem("role") === "ROLE_retreatOwner") return "retreats";
                else if (window.sessionStorage.getItem("role") === "ROLE_shipOwner") return "ships";
                else if (window.sessionStorage.getItem("role") === "ROLE_fishingInstructor") return "adventures";
            }
        },
        validations: {
            form: {
                comment: {
                    required,
                    minLength: minLength(10)
                }
            }
        }
    })
</script>