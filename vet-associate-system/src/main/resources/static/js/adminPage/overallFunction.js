/**
 * 
 */
document.getElementById("navAdminManageOwner").style.display = 'none';
document.getElementById("navAdminManageVet").style.display = 'none';
document.getElementById("navAdminManageCH").style.display = 'none';

function toggleManageList() {
	if (document.getElementById("navAdminManageOwner").style.display == 'none') {
		document.getElementById("navAdminManageOwner").style.display = 'block';
		document.getElementById("navAdminManageVet").style.display = 'block';
		document.getElementById("navAdminManageCH").style.display = 'block';

	} else {
		document.getElementById("navAdminManageOwner").style.display = 'none'
		document.getElementById("navAdminManageVet").style.display = 'none'
		document.getElementById("navAdminManageCH").style.display = 'none';

	}
}
