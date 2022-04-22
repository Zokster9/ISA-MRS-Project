<template>
    <div class="d-flex flex-row">
        <div>
            <NavbarAdmin></NavbarAdmin>
        </div>
        <div class="mx-auto">
        </div>
    </div>
</template>

<script>
    import NavbarAdmin from '@/components/NavbarAdmin.vue'

    export default {
        name: 'ProfilePageAdmin',
        components: {
            NavbarAdmin,
        }
    }
</script>