document.addEventListener('DOMContentLoaded', function() {
    const regionSelect = document.getElementById('region');
    const subregionSelect = document.getElementById('subregion');

    const regions = {
        'seoul': ['노원구', '강남구', '서초구', '송파구', '영등포구', '구로구', '도봉구', '은평구'],
        'gyeonggi': ['수원시', '성남시', '용인시', '파주시', '평택시', '의정부시', '동두천시']
    };

    regionSelect.addEventListener('change', function() {
        const selectedRegion = regionSelect.value;
        const subregions = regions[selectedRegion] || [];
        
        subregionSelect.innerHTML = '<option value="">먼저 지역을 선택해주세요</option>';
        
        subregions.forEach(function(subregion) {
            const option = document.createElement('option');
            option.value = subregion.toLowerCase().replace(/ /g, '');
            option.textContent = subregion;
            subregionSelect.appendChild(option);
        });
    });
});