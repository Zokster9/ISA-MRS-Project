<template>
    <div class="revisions">    
            <div class="app">
                <div class="vertical-center">
                    <div class="inner-block">
                        <div class="revisions">
                            <form @submit.prevent>
                                <div class="form-group required">
                                    <label class="control-label">Enter your rating: </label>
                                    <input v-model="form.rating" type="number" min="1" max="5" class="form-control form-control-lg"/>
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

    Vue.use(VueAxios, axios)
    Vue.use(Vuelidate)

    const isRating = (value) => RegExp(/^[1-5](\.\d{0,1})?$/).test(value);

    export default {
        name: 'ClientRevision',
        data() {
            return {
                form: {
                    rating: 1,
                    revision: '',
                }
            }
        },
        methods: {
            review() {
                axios.post()
            }
        },
        validations: {
            form: {
                rating: {
                    required,
                    isRating
                },
                revision: {
                    required
                }
            }
        }
    }
</script>
