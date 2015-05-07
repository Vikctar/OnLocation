package com.vikcandroid.onlocation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

 /*
Fragment representing a section of the app
 */
public class CategoriesSectionFragment extends Fragment {
    //public  static final String ARG_SECTION_NUMBER = "section_number";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_categories_section, container, false);

        GridView gridView = (GridView) rootView.findViewById(R.id.grid_view);
        gridView.setAdapter(new CatAdapter(rootView.getContext()));

        // Add a click listener (OnItemClickListener) to the gridView items
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), CompanyActivity.class).
                        putExtra(Intent.EXTRA_TEXT, position);
                startActivity(intent);

//                switch (position) {
//                    case 0:
//
//
//                        // Create new fragment and transaction
//                        Fragment bizFragment = new BusinessServices();
//                        FragmentTransaction bizTransaction = getFragmentManager().beginTransaction();
//
//                        // Replace whatever is in the fragment_container view with this fragment,
//                        // and add the transaction to the back stack
//                        bizTransaction.replace(R.id.fragment_container, bizFragment);
//                        bizTransaction.addToBackStack(null);
//
//                        // Commit the transaction
//                        bizTransaction.commit();
//                        break;
//
//                    case 1:
//                        // Create new fragment and transaction
//                        Fragment entFragment = new Entertainment();
//                        FragmentTransaction entTransaction = getFragmentManager().beginTransaction();
//
//                        // Replace whatever is in the fragment_container view with this fragment,
//                        // and add the transaction to the back stack
//                        entTransaction.replace(R.id.fragment_container, entFragment);
//                        entTransaction.addToBackStack(null);
//
//                        // Commit the transaction
//                        entTransaction.commit();
//                        break;
//                }
            }
        });




        return rootView;
    }

    private class CatAdapter extends BaseAdapter {
        private List<CatAdapter.Item> items = new ArrayList<>();
        private LayoutInflater mInflater;

        public CatAdapter(Context context) {
            mInflater = LayoutInflater.from(context);

            items.add(new CatAdapter.Item(R.drawable.icon_business_services));
            items.add(new CatAdapter.Item(R.drawable.icon_entertainment));
            items.add(new CatAdapter.Item(R.drawable.icon_shopping));
            items.add(new CatAdapter.Item(R.drawable.icon_food));
            items.add(new CatAdapter.Item(R.drawable.buildings));
            items.add(new CatAdapter.Item(R.drawable.icon_accomodation));
            items.add(new CatAdapter.Item(R.drawable.icon_healthcare));
            items.add(new CatAdapter.Item(R.drawable.icon_financial));
            items.add(new CatAdapter.Item(R.drawable.icon_government));
            items.add(new CatAdapter.Item(R.drawable.icon_properties));
            items.add(new CatAdapter.Item(R.drawable.icon_transport));
            items.add(new CatAdapter.Item(R.drawable.icon_automotive));
            items.add(new CatAdapter.Item(R.drawable.icon_industry));
            items.add(new CatAdapter.Item(R.drawable.icon_public));
            items.add(new CatAdapter.Item(R.drawable.icon_personal));
            items.add(new CatAdapter.Item(R.drawable.icon_sports));
            items.add(new CatAdapter.Item(R.drawable.icon_computer));
            items.add(new CatAdapter.Item(R.drawable.icon_hot_deals));
            items.add(new CatAdapter.Item(R.drawable.icon_business_contacts));
            items.add(new CatAdapter.Item(R.drawable.icon_building_construction));

        }




        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return items.get(position).drawableId;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;

            ImageView icon;

            if (convertView == null) {
                view = mInflater.inflate(R.layout.grid_view_item, parent, false);
                view.setTag(R.id.icon, view.findViewById(R.id.icon));

            }
            icon = (ImageView)view.getTag(R.id.icon);

            CatAdapter.Item item = (CatAdapter.Item)getItem(position);

            icon.setImageResource(item.drawableId);

            return view;
        }

        private class Item {
            final int drawableId;

            Item(int drawableId) {
                this.drawableId = drawableId;
            }
        }
    }
}
