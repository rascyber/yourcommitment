package es.cylicon.yourcommitment.activity;

import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import es.cylicon.yourcommitment.MapActivity;
import es.cylicon.yourcommitment.R;

public class MenuFragment extends RoboFragment implements OnClickListener {

	@InjectView(R.id.map)
	private TextView map;

	@InjectView(R.id.profile)
	private TextView profile;

	@InjectView(R.id.proyects)
	private TextView proyects;

	@InjectView(R.id.ongs)
	private TextView ongs;

	@InjectView(R.id.amountLeft)
	private TextView amountLeft;

	@Override
	public View onCreateView(final LayoutInflater inflater,
			final ViewGroup container, final Bundle savedInstanceState) {
		return inflater.inflate(R.layout.menu, container, false);
	}

	@Override
	public void onResume() {
		super.onResume();
		profile.setOnClickListener(this);
		proyects.setOnClickListener(this);
		map.setOnClickListener(this);
		ongs.setOnClickListener(this);
		clickMenu();
		final YourCommitmentApplication application = (YourCommitmentApplication) getActivity()
				.getApplication();
		if (application.getCurrentUser() != null) {
			amountLeft.setText(application.getCurrentUser().getAmount()
					.toString()
					+ "€");
		}
	}

	private void clickMenu() {
		if (getActivity().findViewById(R.id.userAmount) != null) {
			profile.setBackgroundColor(getActivity().getResources().getColor(
					R.color.lightCream));
		} else if (getActivity().findViewById(R.id.proyectsActivity) != null) {
			proyects.setBackgroundColor(getActivity().getResources().getColor(
					R.color.lightCream));
		} else if (getActivity().findViewById(R.id.mapa) != null) {
			map.setBackgroundColor(getActivity().getResources().getColor(
					R.color.lightCream));
		} else if (getActivity().findViewById(R.id.organizationsActivity) != null) {
			ongs.setBackgroundColor(getActivity().getResources().getColor(
					R.color.lightCream));
		}

	}

	@Override
	public void onClick(final View v) {
		final Intent intent = new Intent(getActivity(), getClass(v.getId()));
		intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		startActivity(intent);
	}

	private Class<?> getClass(final int id) {
		switch (id) {
		case R.id.map:
			return MapActivity.class;
		case R.id.profile:
			return UserActivity.class;
		case R.id.proyects:
			return ProyectsActivity.class;
		case R.id.ongs:
			return ONGSActivity.class;
		default:
			break;
		}
		return ProyectsActivity.class;
	}

}
