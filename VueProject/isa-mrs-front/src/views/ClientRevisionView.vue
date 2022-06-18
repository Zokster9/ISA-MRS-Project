<template>
    <div class="revisions">    
            <div class="app">
                <div class="vertical-center">
                    <div class="inner-block">
                        <div class="revisions">
                            <form @submit.prevent>
                                <div class="form-group required">
                                    <label class="control-label">Enter your service rating: </label>
                                    <input v-model="form.serviceRating" type="number" min="1" max="5" class="form-control form-control-lg"/>
                                </div>
                                <div class="form-group required">
                                    <label class="control-label">Enter your service owner rating: </label>
                                    <input v-model="form.ownerRating" type="number" min="1" max="5" class="form-control form-control-lg"/>
                                </div>
                                <div class="form-group required">
                                    <label class="control-label">Write your revision: </label>
                                    <textarea v-model="form.revision" placeholder="Please enter your revision..." class="form-control form-control-lg"></textarea>
                                </div>
                                <div class="form-group">
                                    <button @click="review" :disabled="$v.$invalid" type="submit" class="btn btn-dark btn-lg btn-block">Send revision</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
</template>

<script>
    import Vuelidate from 'vuelidate'
    import Vue from 'vue'
    import { required, } from 'vuelidate/lib/validators'
    import axios from 'axios'
    import VueAxios from 'vue-axios'
import router from '@/router'

    Vue.use(VueAxios, axios)
    Vue.use(Vuelidate)

    const isRating = (value) => RegExp(/^[1-5](\.\d{0,1})?$/).test(value);

    export default {
        name: 'ClientRevision',
        data() {
            return {
                form: {
                    serviceRating: 1,
                    ownerRating: 1,
                    revision: '',
                }
            }
        },
        methods: {
            review() {
                axios.post("http://localhost:8088/revisions/makeARevision", {
                    reservationId: parseInt(this.$route.params.id),
                    revision: this.form.revision,
                    serviceRating: this.form.serviceRating,
                    ownerRating: this.form.ownerRating,
                },
                {
                    headers: {
                        Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
                    }
                })
                .then(() => {
                    alert("You have successfully reviewed a reservation!");
                    this.$router.back();
                })
                .catch(() => {
                    alert("Try again later!");
                })
            }
        },
        validations: {
            form: {
                serviceRating: {
                    required,
                    isRating
                },
                ownerRating: {
                    required,
                    isRating
                },
                revision: {
                    required
                }
            }
        },
        mounted() {
            if (window.sessionStorage.getItem("role") != "ROLE_client") {
                router.push("/");
            }
        }
    }
</script>
