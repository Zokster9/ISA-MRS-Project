<template>
    <tr :key="user.id" :class="{activeDeleteButton: hoverDeleteButton}">
        <td class="align-items text-center"> {{ user.name }} {{user.surname}}</td>
        <td class="align-items text-center"> {{ user.email }} </td>
        <td class="align-items text-center"> {{ user.privilegedUser }} </td>
        <td class="align-items"> <button type="button" class="btn btn-danger" @mouseover="hoverDeleteButton = true" 
            @mouseleave="hoverDeleteButton = false" @click="deleteUser(user.id)"> Delete user </button> </td>
    </tr>
</template>

<script>
    import Vue from 'vue' 
    import axios from 'axios'
    import VueAxios from 'vue-axios'
    //import router from '@/router'

    Vue.use(VueAxios, axios)

    export default {
        props: ["user"],
        methods: {
            deleteUser(id){
                axios.delete("http://localhost:8088/users/delete/" + id,{
                    headers:{
                        Authorization: "Bearer " + window.sessionStorage.getItem("accessToken")
                    }
                }).then(() =>{
                    alert("Successfully deleted user")
                    window.location.reload()
                })
            }
        },
        data(){
            return {
                hoverDeleteButton: false,
            }
        },  
    }
</script>