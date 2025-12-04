document.addEventListener('DOMContentLoaded', function(){
    const input = document.getElementById('extInput');
    if (!input) return;
    input.addEventListener('input', function(){
        this.value = this.value.trim().toLowerCase();
        if (this.value.length > 20) this.value = this.value.slice(0,20);
    });
});
